package com.sdeli.deliveryapi.domain.exceptions;

public class PaymentTypeNotFoundException extends EntityNotFoundException {

    public PaymentTypeNotFoundException(Long id) {
        super(String.format("Payment type with id %d was not found.", id));
    }

}
