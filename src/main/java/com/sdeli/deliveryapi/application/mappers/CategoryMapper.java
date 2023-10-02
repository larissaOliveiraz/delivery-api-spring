package com.sdeli.deliveryapi.application.mappers;

import com.sdeli.deliveryapi.application.dto.CategoryDTO;
import com.sdeli.deliveryapi.application.dto.input.CategoryInput;
import com.sdeli.deliveryapi.domain.model.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    private final ModelMapper modelMapper;

    public CategoryMapper(ModelMapper modelMapper) {
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

    public void copyToDomain(CategoryInput categoryInput, Category category) {
        modelMapper.map(categoryInput, category);
    }
}
