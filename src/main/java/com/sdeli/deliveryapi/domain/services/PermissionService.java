package com.sdeli.deliveryapi.domain.services;

import com.sdeli.deliveryapi.domain.exceptions.PermissionNotFoundException;
import com.sdeli.deliveryapi.domain.model.Permission;
import com.sdeli.deliveryapi.domain.repositories.PermissionRepository;
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
