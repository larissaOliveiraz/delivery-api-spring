package com.sdeli.deliveryapi.domain.services;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

public interface ProductPhotoStorageService {

    void upload(NewPhoto newPhoto);

    void delete(String filename);

    default void replace(String existingPhoto, NewPhoto newPhoto) {
        this.upload(newPhoto);

        if (existingPhoto != null) {
            this.delete(existingPhoto);
        }
    }

    default String generateFilename(String filename) {
        return UUID.randomUUID() + "_" + filename;
    }

    @Getter
    @Builder
    class NewPhoto {

        private String filename;
        private InputStream inputStream;

    }

}
