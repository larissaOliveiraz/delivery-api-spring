package com.sdeli.deliveryapi.domain.exceptions;

public abstract class EntityNotFoundException extends GeneralBusinessException {

    public EntityNotFoundException(String message) {
        super(message);
    }

}
