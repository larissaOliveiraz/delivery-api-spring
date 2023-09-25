package com.sdeli.deliveryapi.exceptions;

public class PermissionNotFoundException extends EntityNotFoundException {

    public PermissionNotFoundException(Long id) {
        super(String.format("Permission with id %d was not found.", id));
    }

}
