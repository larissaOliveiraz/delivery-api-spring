package com.sdeli.deliveryapi.domain.services;

import com.sdeli.deliveryapi.domain.model.ProductPhoto;
import com.sdeli.deliveryapi.domain.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductPhotoService {

    private final ProductRepository repository;
    private final ProductPhotoStorageService photoStorageService;

    @Transactional
    public ProductPhoto save(ProductPhoto photo, InputStream fileData) {
        Long restaurantId = photo.getProduct().getRestaurant().getId();
        Long productId = photo.getProduct().getId();

        String uniqueFilename = photoStorageService.generateFilename(photo.getFilename());
        photo.setFilename(uniqueFilename);

        String existingPhoto = null;

        Optional<ProductPhoto> photoExists =
                repository.findPhotoById(restaurantId, productId);

        if (photoExists.isPresent()) {
            existingPhoto = photoExists.get().getFilename();
            repository.delete(photoExists.get());
        }

        photo = repository.save(photo);
        repository.flush();

        ProductPhotoStorageService.NewPhoto newPhoto =
                ProductPhotoStorageService.NewPhoto.builder()
                        .filename(photo.getFilename())
                        .inputStream(fileData)
                        .build();

        photoStorageService.replace(existingPhoto, newPhoto);

        return photo;
    }

}
