package by.glavdel.digital.order.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class OrderTextResponse {
    private Long id;
    private BigDecimal summa;
    private String message;
}