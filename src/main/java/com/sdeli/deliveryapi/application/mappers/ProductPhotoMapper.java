package com.sdeli.deliveryapi.application.mappers;

import com.sdeli.deliveryapi.application.dto.ProductPhotoDTO;
import com.sdeli.deliveryapi.domain.model.ProductPhoto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ProductPhotoMapper {

    private final ModelMapper modelMapper;

    public ProductPhotoDTO toDTO(ProductPhoto photo) {
        return modelMapper.map(photo, ProductPhotoDTO.class);
    }

}
