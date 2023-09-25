package com.sdeli.deliveryapi.controllers;

import com.sdeli.deliveryapi.dto.StateDTO;
import com.sdeli.deliveryapi.dto.factories.MakeStateDTO;
import com.sdeli.deliveryapi.dto.input.StateInput;
import com.sdeli.deliveryapi.model.State;
import com.sdeli.deliveryapi.repositories.StateRepository;
import com.sdeli.deliveryapi.services.StateService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/states")
public class StateController {

    private final StateRepository repository;
    private final StateService service;
    private final MakeStateDTO makeDTO;

    @GetMapping
    public List<StateDTO> findAll() {
        List<State> states = repository.findAll();
        return makeDTO.toCollectionDTO(states);
    }

    @GetMapping("/{id}")
    public StateDTO findById(@PathVariable Long id) {
        State state = service.findByIdOrThrow(id);
        return makeDTO.toDTO(state);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StateDTO save(@RequestBody @Valid StateInput stateInput) {
        State state = makeDTO.toDomain(stateInput);

        state = service.save(state);

        return makeDTO.toDTO(state);
    }

    @PutMapping("/{id}")
    public StateDTO update(@PathVariable Long id,
                           @RequestBody @Valid StateInput stateInput) {
        State state = service.findByIdOrThrow(id);

        makeDTO.copyToDomain(stateInput, state);

        state = service.save(state);

        return makeDTO.toDTO(state);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
