package by.glavdel.digital.position.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PositionResponse {
    private Long positionId;
    private String name;
    private String description;
    private Boolean isRemove;
}
