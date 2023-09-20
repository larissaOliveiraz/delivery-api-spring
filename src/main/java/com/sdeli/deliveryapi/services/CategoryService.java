package com.sdeli.deliveryapi.services;

import com.sdeli.deliveryapi.exceptions.CategoryNotFoundException;
import com.sdeli.deliveryapi.model.Category;
import com.sdeli.deliveryapi.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category save(Category category) {
        return repository.save(category);
    }

    public Category findByIdOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

}
