package by.glavdel.digital.position.controller;

import by.glavdel.digital.position.request.PositionRequest;
import by.glavdel.digital.position.request.PositionUpdateRequest;
import by.glavdel.digital.position.response.PositionResponse;
import by.glavdel.digital.position.response.PositionTextResponse;
import by.glavdel.digital.position.service.PositionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/positions")
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;

    @GetMapping("/{id}")
    public PositionResponse getById(@PathVariable Long id) {
        log.info("...Method getById");
        return positionService.getById(id);
    }

    @PostMapping
    public PositionResponse create(@Valid @RequestBody PositionRequest request) {
        log.info("...Method create");
        return positionService.create(request);
    }

    @PutMapping("/{id}")
    public PositionResponse update(@PathVariable Long id,
                                   @Valid @RequestBody PositionUpdateRequest request) {
        log.info("...Method update");
        return positionService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public PositionTextResponse delete(@PathVariable Long id) {
        log.info("...Method delete");
        return positionService.delete(id);
    }
}
