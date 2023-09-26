package com.sdeli.deliveryapi.exceptions;

public abstract class EntityNotFoundException extends GeneralBusinessException {

    public EntityNotFoundException(String message) {
        super(message);
    }

}
