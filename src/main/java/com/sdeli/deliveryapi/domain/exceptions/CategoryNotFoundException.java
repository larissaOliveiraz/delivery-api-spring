package com.sdeli.deliveryapi.domain.exceptions;

public class CategoryNotFoundException extends EntityNotFoundException {

    public CategoryNotFoundException(Long id) {
        super(String.format("Category with id %d was not found.", id));
    }

}
