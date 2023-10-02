package com.sdeli.deliveryapi.domain.exceptions;

public class OrderNotFoundException extends EntityNotFoundException {

    public OrderNotFoundException(String code) {
        super(String.format("Order with code %s was not found.", code));
    }

}
