package by.glavdel.digital.user.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UserResponse {
    private Long userId;
    private String username;
    private String email;
    private LocalDate dateBirth;
}