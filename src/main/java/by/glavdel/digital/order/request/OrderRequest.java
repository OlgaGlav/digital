package by.glavdel.digital.order.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderRequest {
    private Long userId;
    private Map<Long, Double> itemsInOrderWithCount;
}
