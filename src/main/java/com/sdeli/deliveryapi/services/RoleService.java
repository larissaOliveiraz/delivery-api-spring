package com.sdeli.deliveryapi.services;

import com.sdeli.deliveryapi.exceptions.RoleNotFoundException;
import com.sdeli.deliveryapi.model.Role;
import com.sdeli.deliveryapi.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoleService {

    private RoleRepository repository;

    public Role findByIdOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException(id));
    }

}
