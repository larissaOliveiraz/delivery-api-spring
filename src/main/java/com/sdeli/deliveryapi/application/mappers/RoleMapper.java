package com.sdeli.deliveryapi.application.mappers;

import com.sdeli.deliveryapi.application.dto.RoleDTO;
import com.sdeli.deliveryapi.application.dto.input.RoleInput;
import com.sdeli.deliveryapi.domain.model.Role;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class RoleMapper {

    private final ModelMapper modelMapper;

    public RoleDTO toDTO(Role role) {
        return modelMapper.map(role, RoleDTO.class);
    }

    public List<RoleDTO> toCollectionDTO(Collection<Role> roles) {
        return roles.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Role toDomain(RoleInput roleInput) {
        return modelMapper.map(roleInput, Role.class);
    }

    public void copyToDomain(RoleInput roleInput, Role role) {
        modelMapper.map(roleInput, role);
    }
}
