package com.sdeli.deliveryapi.dto.factories;

import com.sdeli.deliveryapi.dto.RoleDTO;
import com.sdeli.deliveryapi.model.Role;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MakeRoleDTO {

    private final ModelMapper modelMapper;

    public RoleDTO toDTO(Role role) {
        return modelMapper.map(role, RoleDTO.class);
    }
}
