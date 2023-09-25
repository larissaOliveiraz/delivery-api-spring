package com.sdeli.deliveryapi.controllers;

import com.sdeli.deliveryapi.dto.RoleDTO;
import com.sdeli.deliveryapi.dto.factories.MakeRoleDTO;
import com.sdeli.deliveryapi.model.User;
import com.sdeli.deliveryapi.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users/{userId}/roles")
public class UserRoleController {

    private final UserService userService;
    private final MakeRoleDTO makeDTO;

    @GetMapping
    public List<RoleDTO> findAll(@PathVariable Long userId) {
        User user = userService.findByIdOrThrow(userId);

        return makeDTO.toCollectionDTO(user.getRoles());
    }

}
