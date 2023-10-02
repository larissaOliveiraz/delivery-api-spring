package com.sdeli.deliveryapi.application.controllers.restaurant;

import com.sdeli.deliveryapi.application.dto.CategoryDTO;
import com.sdeli.deliveryapi.application.mappers.CategoryMapper;
import com.sdeli.deliveryapi.application.dto.input.CategoryInput;
import com.sdeli.deliveryapi.domain.model.Category;
import com.sdeli.deliveryapi.domain.repositories.CategoryRepository;
import com.sdeli.deliveryapi.domain.services.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository repository;
    private final CategoryService service;
    private final CategoryMapper makeDTO;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO save(@RequestBody @Valid CategoryInput categoryInput) {
        Category category = makeDTO.toDomain(categoryInput);
        category = service.save(category);
        return makeDTO.toDTO(category);
    }

    @PutMapping("/{id}")
    public CategoryDTO update(@PathVariable Long id,
                              @RequestBody @Valid CategoryInput categoryInput) {
        Category category = service.findByIdOrThrow(id);

        makeDTO.copyToDomain(categoryInput, category);

        category = service.save(category);

        return makeDTO.toDTO(category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
