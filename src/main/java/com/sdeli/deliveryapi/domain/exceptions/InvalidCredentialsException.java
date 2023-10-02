package com.sdeli.deliveryapi.domain.exceptions;

public class InvalidCredentialsException extends EntityNotFoundException {

    public InvalidCredentialsException() {
        super("Invalid credentials.");
    }

}
