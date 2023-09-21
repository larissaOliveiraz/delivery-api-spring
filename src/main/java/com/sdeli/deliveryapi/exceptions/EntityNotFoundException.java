package com.sdeli.deliveryapi.exceptions;

public abstract class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }

}
