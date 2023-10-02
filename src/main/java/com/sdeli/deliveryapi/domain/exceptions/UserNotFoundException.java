package com.sdeli.deliveryapi.domain.exceptions;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(Long id) {
        super(String.format("User with id %d was not found.", id));
    }

}
