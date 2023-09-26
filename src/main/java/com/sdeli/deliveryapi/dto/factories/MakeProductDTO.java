package com.sdeli.deliveryapi.dto.factories;

import com.sdeli.deliveryapi.dto.ProductDTO;
import com.sdeli.deliveryapi.dto.input.ProductInput;
import com.sdeli.deliveryapi.model.Product;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class MakeProductDTO {

    private final ModelMapper modelMapper;

    public ProductDTO toDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    public List<ProductDTO> toCollectionDTO(Collection<Product> products) {
        return products.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Product toDomain(ProductInput productInput) {
        return modelMapper.map(productInput, Product.class);
    }

    public void copyToDomain(ProductInput productInput, Product product) {
        modelMapper.map(productInput, product);
    }
}
