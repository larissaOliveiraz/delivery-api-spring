package com.sdeli.deliveryapi.domain.services;

import com.sdeli.deliveryapi.domain.exceptions.EntityInUseException;
import com.sdeli.deliveryapi.domain.exceptions.RoleNotFoundException;
import com.sdeli.deliveryapi.domain.model.Permission;
import com.sdeli.deliveryapi.domain.model.Role;
import com.sdeli.deliveryapi.domain.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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

    public void delete(Long id) {
        try {
            findByIdOrThrow(id);
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException("Role", id);
        }
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
