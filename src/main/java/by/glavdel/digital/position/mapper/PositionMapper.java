package by.glavdel.digital.position.mapper;

import by.glavdel.digital.position.entity.Position;
import by.glavdel.digital.position.request.PositionRequest;
import by.glavdel.digital.position.response.PositionResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PositionMapper {
    public PositionResponse entityToResponse(Position entity) {
        return PositionResponse.builder()
                .positionId(entity.getId())
                .name(entity.getName())
                .isRemove(entity.getIsRemove())
                .description(entity.getDescription())
                .build();
    }

    public Position requestToEntity(@NotNull PositionRequest request) {
        Position position = new Position();
        position.setName(request.getName());
        position.setDescription(request.getDescription());
        position.setIsRemove(false);
        return position;
    }
}

