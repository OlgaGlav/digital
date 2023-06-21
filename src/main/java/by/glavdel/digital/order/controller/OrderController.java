package by.glavdel.digital.order.controller;

import by.glavdel.digital.order.request.OrderRequest;
import by.glavdel.digital.order.response.OrderResponse;
import by.glavdel.digital.order.response.OrderTextResponse;
import by.glavdel.digital.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public OrderResponse getById(@PathVariable Long id) {
        log.info("...Method getById");
        return orderService.getById(id);
    }

    @PostMapping
    public OrderResponse createOrder(@Valid @RequestBody OrderRequest request) {
        log.info("...Method createItem");
        return orderService.create(request);
    }

    @DeleteMapping("/{id}")
    public OrderTextResponse delete(@PathVariable Long id) {
        log.info("...Method delete");
        return orderService.delete(id);
    }
}
