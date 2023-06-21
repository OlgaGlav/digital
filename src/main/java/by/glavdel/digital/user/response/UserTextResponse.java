package by.glavdel.digital.user.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserTextResponse {
    private String username;
    private String email;
    private String message;
}