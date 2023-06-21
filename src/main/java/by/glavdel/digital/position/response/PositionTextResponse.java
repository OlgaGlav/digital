package by.glavdel.digital.position.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PositionTextResponse {
    private Long id;
    private String name;
    private String message;
}