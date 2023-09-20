package com.sdeli.deliveryapi.dto.factories;

import com.sdeli.deliveryapi.dto.CategoryDTO;
import com.sdeli.deliveryapi.dto.input.CategoryInput;
import com.sdeli.deliveryapi.model.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MakeCategoryDTO {

    private final ModelMapper modelMapper;

    public MakeCategoryDTO(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CategoryDTO toDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    public List<CategoryDTO> toCollectionDTO(List<Category> categories) {
        return categories.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Category toDomain(CategoryInput categoryInput) {
        return modelMapper.map(categoryInput, Category.class);
    }
}
