package com.sdeli.deliveryapi.domain.services;

import com.sdeli.deliveryapi.domain.exceptions.CategoryNotFoundException;
import com.sdeli.deliveryapi.domain.exceptions.EntityInUseException;
import com.sdeli.deliveryapi.domain.model.Category;
import com.sdeli.deliveryapi.domain.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository repository;

    public Category save(Category category) {
        return repository.save(category);
    }

    public void delete(Long id) {
        try {
            findByIdOrThrow(id);
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException("Category", id);
        }
    }

    public Category findByIdOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

}
