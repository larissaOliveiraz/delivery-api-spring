package com.sdeli.deliveryapi.infra.service.storage;

import com.sdeli.deliveryapi.domain.services.ProductPhotoStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LocalProductPhotoStorageService implements ProductPhotoStorageService {

    @Value("${delivery.storage.local.photo-directory}")
    private Path photoDirectory;

    @Override
    public void upload(NewPhoto newPhoto) {
        try {
            Path filePath = getFilePath(newPhoto.getFilename());

            FileCopyUtils.copy(newPhoto.getInputStream(),
                    Files.newOutputStream(filePath));
        } catch (IOException e) {
            throw new StorageException("Failed to upload file.", e);
        }
    }

    @Override
    public void delete(String filename) {
        try {
            Path filePath = getFilePath(filename);

            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new StorageException("Failed to delete file.", e);
        }
    }

    private Path getFilePath(String filename) {
        return photoDirectory.resolve(Path.of(filename));
    }
}
