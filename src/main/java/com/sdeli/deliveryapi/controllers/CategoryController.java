package com.sdeli.deliveryapi.controllers;

import com.sdeli.deliveryapi.model.Category;
import com.sdeli.deliveryapi.repositories.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository repository;

    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Category> findAll() {
        return repository.findAll();
    }

}
