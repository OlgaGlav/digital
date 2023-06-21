package by.glavdel.digital.item.mapper;

import by.glavdel.digital.exception.ExceptionMessageConstants;
import by.glavdel.digital.item.entity.Item;
import by.glavdel.digital.item.request.ItemRequest;
import by.glavdel.digital.item.responce.ItemResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ItemMapper {
    public ItemResponse entityToResponse(Item entity) {
        return ItemResponse.builder()
                .itemId(entity.getId())
                .positionId(entity.getPosition().getId())
                .price(entity.getPrice())
                .actualCount(entity.getActualCount())
                .build();
    }

    public Item requestToEntity(@NotNull ItemRequest request) {
        Item item = new Item();
        if (Double.parseDouble(request.getPrice()) < 0) {
            throw new IllegalArgumentException(ExceptionMessageConstants.NOT_VALID_PRICE);
        } else {
            item.setPrice(BigDecimal.valueOf(Double.parseDouble(request.getPrice())));
        }
        item.setActualCount(request.getActualCount());
        item.setIsRemove(false);
        return item;
    }
}

