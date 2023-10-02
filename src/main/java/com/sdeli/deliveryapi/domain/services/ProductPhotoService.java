package com.sdeli.deliveryapi.domain.services;

import com.sdeli.deliveryapi.domain.model.ProductPhoto;
import com.sdeli.deliveryapi.domain.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ProductPhotoService {

    private final ProductRepository repository;

    @Transactional
    public ProductPhoto save(ProductPhoto photo) {
        return repository.save(photo);
    }

}
