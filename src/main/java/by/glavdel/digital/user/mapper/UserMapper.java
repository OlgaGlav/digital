package by.glavdel.digital.user.mapper;

import by.glavdel.digital.user.entity.User;
import by.glavdel.digital.user.request.UserRequest;
import by.glavdel.digital.user.response.UserResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserResponse entityToResponse(@NotNull User entity) {
        return UserResponse.builder()
                .userId(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .dateBirth(entity.getDateBirth())
                .build();
    }

    public User requestToEntity(@NotNull UserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setDateBirth(request.getDateBirth());
        return user;
    }
}
