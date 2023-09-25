package com.sdeli.deliveryapi.services;

import com.sdeli.deliveryapi.exceptions.PermissionNotFoundException;
import com.sdeli.deliveryapi.model.Permission;
import com.sdeli.deliveryapi.repositories.PermissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PermissionService {

    private final PermissionRepository repository;

    public Permission findByIdOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PermissionNotFoundException(id));
    }

}
