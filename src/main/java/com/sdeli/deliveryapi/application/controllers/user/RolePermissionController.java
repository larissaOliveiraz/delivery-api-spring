package com.sdeli.deliveryapi.application.controllers.user;

import com.sdeli.deliveryapi.application.dto.PermissionDTO;
import com.sdeli.deliveryapi.application.mappers.PermissionMapper;
import com.sdeli.deliveryapi.domain.model.Role;
import com.sdeli.deliveryapi.domain.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/roles/{roleId}/permissions")
public class RolePermissionController {

    private final RoleService service;
    private final PermissionMapper permissionMapper;

    @GetMapping
    public List<PermissionDTO> findAll(@PathVariable Long roleId) {
        Role role = service.findByIdOrThrow(roleId);

        return permissionMapper.toCollectionDTO(role.getPermissions());
    }

    @PutMapping("/{permissionId}")
    public void addPermission(@PathVariable Long roleId,
                              @PathVariable Long permissionId) {
        service.addPermission(roleId, permissionId);
    }

    @DeleteMapping("/{permissionId}")
    public void removePermission(@PathVariable Long roleId,
                              @PathVariable Long permissionId) {
        service.removePermission(roleId, permissionId);
    }

}
