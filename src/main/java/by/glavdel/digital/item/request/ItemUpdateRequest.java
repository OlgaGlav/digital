package by.glavdel.digital.item.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemUpdateRequest {

    @NotEmpty
    private String price;
    @Positive
    private Double actualCount;
}
