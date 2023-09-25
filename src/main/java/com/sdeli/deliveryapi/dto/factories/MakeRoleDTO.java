package com.sdeli.deliveryapi.dto.factories;

import com.sdeli.deliveryapi.dto.RoleDTO;
import com.sdeli.deliveryapi.dto.input.RoleInput;
import com.sdeli.deliveryapi.model.Role;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class MakeRoleDTO {

    private final ModelMapper modelMapper;

    public RoleDTO toDTO(Role role) {
        return modelMapper.map(role, RoleDTO.class);
    }

    public List<RoleDTO> toCollectionDTO(List<Role> roles) {
        return roles.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Role toDomain(RoleInput roleInput) {
        return modelMapper.map(roleInput, Role.class);
    }
}
