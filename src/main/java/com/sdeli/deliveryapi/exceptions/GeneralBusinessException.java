package com.sdeli.deliveryapi.exceptions;

public class GeneralBusinessException extends RuntimeException {

    public GeneralBusinessException(String message) {
        super(message);
    }

    public GeneralBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

}
