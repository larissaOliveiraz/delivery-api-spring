package com.sdeli.deliveryapi.application.controllers.restaurant;

import com.sdeli.deliveryapi.application.dto.ProductPhotoDTO;
import com.sdeli.deliveryapi.application.dto.input.ProductPhotoInput;
import com.sdeli.deliveryapi.application.mappers.ProductPhotoMapper;
import com.sdeli.deliveryapi.domain.exceptions.EntityNotFoundException;
import com.sdeli.deliveryapi.domain.model.Product;
import com.sdeli.deliveryapi.domain.model.ProductPhoto;
import com.sdeli.deliveryapi.domain.services.ProductPhotoService;
import com.sdeli.deliveryapi.domain.services.ProductPhotoStorageService;
import com.sdeli.deliveryapi.domain.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {

    private final ProductPhotoService photoService;
    private final ProductPhotoStorageService photoStorageService;
    private final ProductService productService;
    private final ProductPhotoMapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductPhotoDTO find(@PathVariable Long restaurantId,
                                @PathVariable Long productId) {
        ProductPhoto photo = photoService.findOrThrow(restaurantId, productId);

        return mapper.toDTO(photo);
    }

    @GetMapping
    public ResponseEntity<InputStreamResource> showPhoto(
            @PathVariable Long restaurantId,
            @PathVariable Long productId,
            @RequestHeader(name = "accept") String acceptHeader
    ) throws HttpMediaTypeNotAcceptableException {
        try {
            ProductPhoto photo = photoService.findOrThrow(restaurantId, productId);

            MediaType photoMediaType = MediaType.parseMediaType(photo.getContentType());
            List<MediaType> acceptedMediaTypes = MediaType.parseMediaTypes(acceptHeader);

            verifyMediaTypeCompatibility(photoMediaType, acceptedMediaTypes);

            InputStream inputStream = photoStorageService.recover(photo.getFilename());

            return ResponseEntity.ok()
                    .contentType(photoMediaType)
                    .body(new InputStreamResource(inputStream));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductPhotoDTO updatePhoto(@PathVariable Long restaurantId,
                                       @PathVariable Long productId,
                                       @Valid ProductPhotoInput productPhotoInput) throws IOException {
        Product product = productService.findByIdOrThrow(restaurantId, productId);
        MultipartFile file = productPhotoInput.getFile();

        ProductPhoto photo = new ProductPhoto();
        photo.setProduct(product);
        photo.setProductId(productId);
        photo.setDescription(productPhotoInput.getDescription());
        photo.setFilename(file.getOriginalFilename());
        photo.setContentType(file.getContentType());
        photo.setSize(file.getSize());

        ProductPhoto productPhoto = photoService.save(photo, file.getInputStream());
        return mapper.toDTO(productPhoto);
    }

    private void verifyMediaTypeCompatibility(MediaType photoMediaType,
                                              List<MediaType> acceptedMediaTypes) throws HttpMediaTypeNotAcceptableException {
        var compatible = acceptedMediaTypes.stream()
                .anyMatch(mediaType -> mediaType.isCompatibleWith(photoMediaType));

        if (!compatible) {
            throw new HttpMediaTypeNotAcceptableException(acceptedMediaTypes);
        }
    }

}
