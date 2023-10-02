package com.sdeli.deliveryapi.domain.exceptions;

public class PhotoNotFoundException extends EntityNotFoundException {

    public PhotoNotFoundException(Long productId) {
        super(String.format("Product with id %d does not have a photo.", productId));
    }

}
