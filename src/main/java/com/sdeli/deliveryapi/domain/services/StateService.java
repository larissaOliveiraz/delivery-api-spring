package com.sdeli.deliveryapi.domain.services;

import com.sdeli.deliveryapi.domain.exceptions.EntityInUseException;
import com.sdeli.deliveryapi.domain.exceptions.StateNotFoundException;
import com.sdeli.deliveryapi.domain.model.State;
import com.sdeli.deliveryapi.domain.repositories.StateRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StateService {

    private final StateRepository repository;

    public State save(State state) {
        return repository.save(state);
    }

    public void delete(Long id) {
        try {
            findByIdOrThrow(id);
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException("State", id);
        }
    }

    public State findByIdOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new StateNotFoundException(id));
    }
}
