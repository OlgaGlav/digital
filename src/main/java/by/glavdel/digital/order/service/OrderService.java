package by.glavdel.digital.order.service;

import by.glavdel.digital.exception.ExceptionMessageConstants;
import by.glavdel.digital.exception.custom.NotFoundException;
import by.glavdel.digital.item.entity.Item;
import by.glavdel.digital.item.repository.ItemRepository;
import by.glavdel.digital.order.entity.Order;
import by.glavdel.digital.order.mapper.OrderMapper;
import by.glavdel.digital.order.repository.OrderRepository;
import by.glavdel.digital.order.request.OrderRequest;
import by.glavdel.digital.order.response.OrderResponse;
import by.glavdel.digital.order.response.OrderTextResponse;
import by.glavdel.digital.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Validated
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    private final OrderMapper orderMapper;

    public OrderResponse getById(Long id) {
        log.info("...Method getById");
        return orderMapper.entityToResponse(orderRepository.findByIdAndIsRemoveFalse(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessageConstants.ORDER_NOT_FOUND)));
    }

    public OrderTextResponse delete(Long id) {
        log.info("...Method delete");
        Order order = orderRepository.findByIdAndIsRemoveFalse(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessageConstants.ORDER_NOT_FOUND));
        order.setIsRemove(true);
        orderRepository.save(order);
        log.info("...Order was deleted");
        return OrderTextResponse.builder()
                .id(order.getId())
                .summa(order.getSumma())
                .message("Order was deleted.")
                .build();
    }

    @Transactional
    public OrderResponse create(OrderRequest request) {
        Order order = orderMapper.requestToEntity(request);
        if (userRepository.findById(request.getUserId()).isPresent()) {
            order.setUser(userRepository.findById(request.getUserId()).get());
        } else {
            throw new NotFoundException(ExceptionMessageConstants.USER_NOT_FOUND);
        }

        request.getItemsInOrderWithCount().keySet().forEach(id -> {
            Item item = itemRepository.findByIdAndIsRemoveFalse(id).get();
            if (item.getActualCount() < request.getItemsInOrderWithCount().get(item.getId())) {
                throw new IllegalArgumentException(ExceptionMessageConstants.NOT_ENOUGH_COUNT);
            }
        });

        List<Item> itemList = request.getItemsInOrderWithCount().keySet().stream()
                .map(id -> itemRepository.findByIdAndIsRemoveFalse(id).get())
                .toList();

        order.setItems(itemList);
        order.setSumma(findSummaAllItems(request.getItemsInOrderWithCount()));
        Order orderSave = orderRepository.save(order);

        reduceCount(request.getItemsInOrderWithCount());
        itemList.forEach(item -> {
            item.getOrders().add(order);
            itemRepository.save(item);
        });

        return orderMapper.entityToResponse(orderSave);
    }

    private BigDecimal findSummaAllItems(Map<Long, Double> itemsInOrderWithCount) {
        List<Item> itemList = itemsInOrderWithCount.keySet().stream()
                .map(itemId -> itemRepository.findByIdAndIsRemoveFalse(itemId)
                        .orElseThrow(() -> new NotFoundException(ExceptionMessageConstants.ITEM_NOT_FOUND)))
                .toList();
        double summa = (double) Math.round(itemList.stream()
                .mapToDouble(item -> item.getPrice().doubleValue() * itemsInOrderWithCount.get(item.getId()))
                .sum() * 100) / 100;
        return BigDecimal.valueOf(summa);
    }

    private void reduceCount(Map<Long, Double> itemsInOrderWithCount) {
        log.info("... findSummaAllItems");
        itemsInOrderWithCount.keySet().forEach(itemId -> {
            Item item = itemRepository.findByIdAndIsRemoveFalse(itemId)
                    .orElseThrow(() -> new NotFoundException(ExceptionMessageConstants.ITEM_NOT_FOUND));
            Double countInOrder = itemsInOrderWithCount.get(itemId);
            checkCount(item, countInOrder);
            item.setActualCount(item.getActualCount() - countInOrder);
            itemRepository.save(item);
        });
    }

    private void checkCount(Item item, Double count) {
        if (item.getActualCount() < count) {
            throw new IllegalArgumentException(ExceptionMessageConstants.NOT_ENOUGH_COUNT);
        }
    }
}
