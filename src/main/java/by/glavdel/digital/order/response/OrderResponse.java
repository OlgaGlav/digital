package by.glavdel.digital.order.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class OrderResponse {
    private Long orderId;
    private Long userId;
    private BigDecimal summa;
    private LocalDate orderDate;
    private List<Long> itemsId;
}