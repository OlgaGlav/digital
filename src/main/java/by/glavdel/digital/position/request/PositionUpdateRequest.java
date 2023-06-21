package by.glavdel.digital.position.request;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PositionUpdateRequest {

    @Pattern(regexp = "^[\\pL\\d]+[\\pL\\pP\\pZ\\d]{2,}$")
    private String name;

    private String description;
}
