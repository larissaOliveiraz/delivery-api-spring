package com.sdeli.deliveryapi.domain.exceptions;

public class StateNotFoundException extends EntityNotFoundException {

    public StateNotFoundException(Long id) {
        super(String.format("State with id %d was not found.", id));
    }

}
