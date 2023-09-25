package com.sdeli.deliveryapi.exceptions;

public class InvalidCredentialsException extends EntityNotFoundException {

    public InvalidCredentialsException() {
        super("Invalid credentials.");
    }

}
