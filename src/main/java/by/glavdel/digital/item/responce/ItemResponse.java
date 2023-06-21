package by.glavdel.digital.item.responce;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ItemResponse {
    private Long itemId;
    private Long positionId;
    private BigDecimal price;
    private Double actualCount;
}
