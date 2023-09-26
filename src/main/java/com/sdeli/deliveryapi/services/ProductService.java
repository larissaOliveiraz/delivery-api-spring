package com.sdeli.deliveryapi.services;

import com.sdeli.deliveryapi.exceptions.ProductNotFoundException;
import com.sdeli.deliveryapi.model.Product;
import com.sdeli.deliveryapi.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository repository;

    public Product findByIdOrThrow(Long restaurantId, Long productId) {
        return repository.findById(restaurantId, productId)
                .orElseThrow(() -> new ProductNotFoundException(restaurantId, productId));
    }

}
