package com.sdeli.deliveryapi.domain.exceptions;

public class RoleNotFoundException extends EntityNotFoundException {

    public RoleNotFoundException(Long id) {
        super(String.format("Role with id %d was not found.", id));
    }

}
