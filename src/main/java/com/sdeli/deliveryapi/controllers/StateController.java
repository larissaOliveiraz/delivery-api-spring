package com.sdeli.deliveryapi.controllers;

import com.sdeli.deliveryapi.dto.StateDTO;
import com.sdeli.deliveryapi.dto.factories.MakeStateDTO;
import com.sdeli.deliveryapi.model.State;
import com.sdeli.deliveryapi.repositories.StateRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    private final StateRepository repository;
    private final MakeStateDTO makeDTO;

    public StateController(StateRepository repository, MakeStateDTO makeDTO) {
        this.repository = repository;
        this.makeDTO = makeDTO;
    }

    @GetMapping
    public List<StateDTO> findAll() {
        List<State> states = repository.findAll();
        return makeDTO.toCollectionDTO(states);
    }

}
