package by.glavdel.digital.position.service;

import by.glavdel.digital.exception.ExceptionMessageConstants;
import by.glavdel.digital.exception.custom.NotFoundException;
import by.glavdel.digital.position.entity.Position;
import by.glavdel.digital.position.mapper.PositionMapper;
import by.glavdel.digital.position.repository.PositionRepository;
import by.glavdel.digital.position.request.PositionRequest;
import by.glavdel.digital.position.request.PositionUpdateRequest;
import by.glavdel.digital.position.response.PositionResponse;
import by.glavdel.digital.position.response.PositionTextResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@RequiredArgsConstructor
@Validated
public class PositionService {

    private final PositionRepository repository;
    private final PositionMapper positionMapper;

    public PositionResponse getById(Long id) {
        log.info("...Method getById");
        return positionMapper.entityToResponse(repository.findByIdAndIsRemoveFalse(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessageConstants.POSITION_NOT_FOUND)));
    }

    public PositionTextResponse delete(Long id) {
        log.info("...Method delete");
        Position position = repository.findByIdAndIsRemoveFalse(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessageConstants.POSITION_NOT_FOUND));
        position.setIsRemove(true);
        repository.save(position);
        log.info("...Position was deleted");
        return PositionTextResponse.builder()
                .name(position.getName())
                .id(position.getId())
                .message("Position was deleted.")
                .build();
    }

    public PositionResponse create(PositionRequest request) {
        return positionMapper.entityToResponse(repository.save(positionMapper.requestToEntity(request)));
    }

    public PositionResponse update(Long id, PositionUpdateRequest request) {
        log.info("...Method update");
        Position position = repository.findByIdAndIsRemoveFalse(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessageConstants.POSITION_NOT_FOUND));
        position.setName(StringUtils.isNotEmpty(request.getName())
                ? request.getName()
                : position.getName());
        position.setDescription(StringUtils.isNotEmpty(request.getDescription())
                ? request.getDescription()
                : position.getDescription());
        Position updatedPosition = repository.save(position);

        return positionMapper.entityToResponse(updatedPosition);
    }
}
