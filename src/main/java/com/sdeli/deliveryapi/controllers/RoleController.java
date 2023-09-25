package com.sdeli.deliveryapi.controllers;

import com.sdeli.deliveryapi.dto.RoleDTO;
import com.sdeli.deliveryapi.dto.factories.MakeRoleDTO;
import com.sdeli.deliveryapi.dto.input.RoleInput;
import com.sdeli.deliveryapi.model.Role;
import com.sdeli.deliveryapi.repositories.RoleRepository;
import com.sdeli.deliveryapi.services.RoleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleRepository repository;
    private final RoleService service;
    private final MakeRoleDTO makeDTO;

    @GetMapping
    public List<RoleDTO> findAll() {
        List<Role> roles = repository.findAll();
        return makeDTO.toCollectionDTO(roles);
    }

    @GetMapping("/{id}")
    public RoleDTO findById(@PathVariable Long id) {
        Role role = service.findByIdOrThrow(id);
        return makeDTO.toDTO(role);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDTO save(@RequestBody @Valid RoleInput roleInput) {
        Role role = makeDTO.toDomain(roleInput);

        role = service.save(role);

        return makeDTO.toDTO(role);
    }

    @PutMapping("/{id}")
    public RoleDTO update(@PathVariable Long id,
                          @RequestBody @Valid RoleInput roleInput) {
        Role role = service.findByIdOrThrow(id);

        makeDTO.copyToDomain(roleInput, role);
        role = service.save(role);

        return makeDTO.toDTO(role);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}