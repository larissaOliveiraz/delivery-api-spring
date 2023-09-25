package com.sdeli.deliveryapi.controllers;

import com.sdeli.deliveryapi.dto.PermissionDTO;
import com.sdeli.deliveryapi.dto.factories.MakePermissionDTO;
import com.sdeli.deliveryapi.model.Role;
import com.sdeli.deliveryapi.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/roles/{roleId}/permissions")
public class RolePermissionController {

    private final RoleService service;
    private final MakePermissionDTO makePermissionDTO;

    @GetMapping
    public List<PermissionDTO> findAll(@PathVariable Long roleId) {
        Role role = service.findByIdOrThrow(roleId);

        return makePermissionDTO.toCollectionDTO(role.getPermissions());
    }

}
