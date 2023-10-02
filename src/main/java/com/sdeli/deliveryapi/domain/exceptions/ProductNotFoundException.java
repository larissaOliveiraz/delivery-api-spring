package com.sdeli.deliveryapi.domain.exceptions;

public class ProductNotFoundException extends EntityNotFoundException {

    public ProductNotFoundException(Long restaurantId, Long productId) {
        super(String.format(
                "Restaurant with id %d doesn't have a product with id %d",
                restaurantId, productId
        ));
    }

}
