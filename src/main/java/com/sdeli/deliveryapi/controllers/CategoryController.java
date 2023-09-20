package com.sdeli.deliveryapi.controllers;

import com.sdeli.deliveryapi.model.Category;
import com.sdeli.deliveryapi.repositories.CategoryRepository;
import com.sdeli.deliveryapi.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository repository;
    private final CategoryService service;

    public CategoryController(CategoryRepository repository, CategoryService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    public List<Category> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable Long id) {
        return service.findByIdOrThrow(id);
    }

}
