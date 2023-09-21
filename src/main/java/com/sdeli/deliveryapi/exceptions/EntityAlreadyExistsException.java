package com.sdeli.deliveryapi.exceptions;

public class EntityAlreadyExistsException extends RuntimeException {

    public EntityAlreadyExistsException (String entity, String name) {
        super(String.format(
                "%s with name '%s' already exists.",
                entity, name
        ));
    }

}
