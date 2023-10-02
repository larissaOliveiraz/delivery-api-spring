package com.sdeli.deliveryapi.domain.exceptions;

public class EntityInUseException extends RuntimeException {

    public EntityInUseException(String entity, Long id) {
        super(String.format(
                "%s with id %d cannot be deleted because it is being used.",
                entity, id
        ));
    }

}
