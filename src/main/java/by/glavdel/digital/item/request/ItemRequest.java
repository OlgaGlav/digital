package by.glavdel.digital.item.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class ItemRequest {

    private Long positionId;
    @NotEmpty
    private String price;
    @Positive
    private Double actualCount;
}
