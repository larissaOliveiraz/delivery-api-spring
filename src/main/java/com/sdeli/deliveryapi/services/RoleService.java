package com.sdeli.deliveryapi.services;

import com.sdeli.deliveryapi.exceptions.RoleNotFoundException;
import com.sdeli.deliveryapi.model.Permission;
import com.sdeli.deliveryapi.model.Role;
import com.sdeli.deliveryapi.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RoleService {

    private final RoleRepository repository;
    private final PermissionService permissionService;

    public Role save(Role role) {
        return repository.save(role);
    }

    @Transactional
    public void addPermission(Long roleId, Long permissionId) {
        Role role = findByIdOrThrow(roleId);
        Permission permission = permissionService.findByIdOrThrow(permissionId);

        role.addPermission(permission);
    }

    @Transactional
    public void removePermission(Long roleId, Long permissionId) {
        Role role = this.findByIdOrThrow(roleId);
        Permission permission = permissionService.findByIdOrThrow(permissionId);

        role.removePermission(permission);
    }

    public Role findByIdOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException(id));
    }

}
