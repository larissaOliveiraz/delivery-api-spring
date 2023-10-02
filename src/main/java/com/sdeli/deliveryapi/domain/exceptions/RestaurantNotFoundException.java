package com.sdeli.deliveryapi.domain.exceptions;

public class RestaurantNotFoundException extends EntityNotFoundException {

    public RestaurantNotFoundException(Long id) {
        super(String.format("Restaurant with id %d was not found.", id));
    }

}
