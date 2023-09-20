package com.sdeli.deliveryapi.controllers;

import com.sdeli.deliveryapi.dto.CategoryDTO;
import com.sdeli.deliveryapi.dto.factories.MakeCategoryDTO;
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
    private final MakeCategoryDTO makeDTO;

    public CategoryController(CategoryRepository repository, CategoryService service, MakeCategoryDTO makeDTO) {
        this.repository = repository;
        this.service = service;
        this.makeDTO = makeDTO;
    }

    @GetMapping
    public List<CategoryDTO> findAll() {
        List<Category> categories = repository.findAll();
        return makeDTO.toCollectionDTO(categories);
    }

    @GetMapping("/{id}")
    public CategoryDTO findById(@PathVariable Long id) {
        Category category = service.findByIdOrThrow(id);
        return makeDTO.toDTO(category);
    }

}
