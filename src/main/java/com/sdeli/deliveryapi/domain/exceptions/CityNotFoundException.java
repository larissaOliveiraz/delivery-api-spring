package com.sdeli.deliveryapi.domain.exceptions;

public class CityNotFoundException extends EntityNotFoundException {

    public CityNotFoundException(Long id) {
        super(String.format("City with id %d was not found.", id));
    }

}
