package com.sdeli.deliveryapi.services;

import com.sdeli.deliveryapi.exceptions.StateNotFoundException;
import com.sdeli.deliveryapi.model.State;
import com.sdeli.deliveryapi.repositories.StateRepository;
import org.springframework.stereotype.Service;

@Service
public class StateService {

    private final StateRepository repository;

    public StateService(StateRepository repository) {
        this.repository = repository;
    }

    public State save(State state) {
        return repository.save(state);
    }

    public State findByIdOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new StateNotFoundException(id));
    }
}
