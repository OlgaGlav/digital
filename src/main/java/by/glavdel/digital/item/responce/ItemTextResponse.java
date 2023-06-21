package by.glavdel.digital.item.responce;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemTextResponse {
    private Long id;
    private String message;
}