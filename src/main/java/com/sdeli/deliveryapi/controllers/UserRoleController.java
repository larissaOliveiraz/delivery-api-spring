package com.sdeli.deliveryapi.controllers;

import com.sdeli.deliveryapi.dto.RoleDTO;
import com.sdeli.deliveryapi.dto.factories.MakeRoleDTO;
import com.sdeli.deliveryapi.model.User;
import com.sdeli.deliveryapi.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users/{userId}/roles")
public class UserRoleController {

    private final UserService service;
    private final MakeRoleDTO makeDTO;

    @GetMapping
    public List<RoleDTO> findAll(@PathVariable Long userId) {
        User user = service.findByIdOrThrow(userId);

        return makeDTO.toCollectionDTO(user.getRoles());
    }

    @PutMapping("/{roleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addRole(@PathVariable Long userId,
                        @PathVariable Long roleId) {
        service.addRole(userId, roleId);
    }

    @DeleteMapping("/{roleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRole(@PathVariable Long userId,
                        @PathVariable Long roleId) {
        service.removeRole(userId, roleId);
    }

}
