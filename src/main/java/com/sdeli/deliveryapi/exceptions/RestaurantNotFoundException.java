package com.sdeli.deliveryapi.exceptions;

public class RestaurantNotFoundException extends EntityNotFoundException {

    public RestaurantNotFoundException(Long id) {
        super(String.format("Restaurant with id %d was not found.", id));
    }

}
