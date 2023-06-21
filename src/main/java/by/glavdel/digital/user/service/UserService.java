package by.glavdel.digital.user.service;

import by.glavdel.digital.exception.ExceptionMessageConstants;
import by.glavdel.digital.exception.custom.AuthenticateException;
import by.glavdel.digital.exception.custom.NotFoundException;
import by.glavdel.digital.user.entity.User;
import by.glavdel.digital.user.mapper.UserMapper;
import by.glavdel.digital.user.repository.RoleRepository;
import by.glavdel.digital.user.repository.UserRepository;
import by.glavdel.digital.user.request.UserRequest;
import by.glavdel.digital.user.request.UserUpdateRequest;
import by.glavdel.digital.user.response.UserResponse;
import by.glavdel.digital.user.response.UserTextResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@RequiredArgsConstructor
@Validated
public class UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    public UserResponse getById(Long id) {
        log.info("...Method getById");
        return userMapper.entityToResponse(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessageConstants.USER_NOT_FOUND)));
    }

    public UserTextResponse delete(Long id) {
        log.info("...Method delete");
        User user = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessageConstants.USER_NOT_FOUND));
        repository.delete(user);
        log.info("...User was delete");
        return UserTextResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .message("User was deleted.")
                .build();
    }

    public UserResponse create(UserRequest request) {
        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new AuthenticateException(ExceptionMessageConstants.USER_WITH_SUCH_EMAIL_EXIST);
        }
        if (repository.findByUsername(request.getUsername()).isPresent()) {
            throw new AuthenticateException(ExceptionMessageConstants.USER_WITH_SUCH_USERNAME_EXIST);
        }
        User user = userMapper.requestToEntity(request);
        user.setRole(StringUtils.isEmpty(request.getRole())
                ? roleRepository.findByName("ROLE_USER")
                : roleRepository.findByName(request.getRole().toUpperCase()));

        return userMapper.entityToResponse(repository.save(user));
    }

    public UserResponse update(Long id, UserUpdateRequest request) {
        log.info("...Method update");
        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException(ExceptionMessageConstants.USER_NOT_FOUND));
        if (id.equals(user.getId())) {
            if (StringUtils.isNotEmpty(request.getUsername())
                    && !request.getUsername().equals(user.getUsername())
                    && repository.findByUsername(request.getUsername()).isPresent()) {
                throw new AuthenticateException(ExceptionMessageConstants.USER_WITH_SUCH_USERNAME_EXIST);
            }
            user.setUsername(StringUtils.isNotEmpty(request.getUsername())
                    ? request.getUsername()
                    : user.getUsername());
            user.setDateBirth(request.getDateBirth() != null
                    ? request.getDateBirth()
                    : user.getDateBirth());
            User updatedUser = repository.save(user);

            return userMapper.entityToResponse(updatedUser);
        }
        throw new AuthenticateException(ExceptionMessageConstants.EMAIL_DONT_MATCH_ID);
    }
}
