package com.sdeli.deliveryapi.application.controllers.restaurant;

import com.sdeli.deliveryapi.application.dto.ProductPhotoDTO;
import com.sdeli.deliveryapi.application.dto.input.ProductPhotoInput;
import com.sdeli.deliveryapi.application.mappers.ProductPhotoMapper;
import com.sdeli.deliveryapi.domain.model.Product;
import com.sdeli.deliveryapi.domain.model.ProductPhoto;
import com.sdeli.deliveryapi.domain.services.ProductPhotoService;
import com.sdeli.deliveryapi.domain.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {

    private final ProductPhotoService photoService;
    private final ProductService productService;
    private final ProductPhotoMapper mapper;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductPhotoDTO updatePhoto(@PathVariable Long restaurantId,
                                       @PathVariable Long productId,
                                       @Valid ProductPhotoInput productPhotoInput) {
        Product product = productService.findByIdOrThrow(restaurantId, productId);
        MultipartFile file = productPhotoInput.getFile();

        ProductPhoto photo = new ProductPhoto();
        photo.setProduct(product);
        photo.setProductId(productId);
        photo.setDescription(productPhotoInput.getDescription());
        photo.setFilename(file.getOriginalFilename());
        photo.setContentType(file.getContentType());
        photo.setSize(file.getSize());

        ProductPhoto productPhoto = photoService.save(photo);
        return mapper.toDTO(productPhoto);
    }

}
