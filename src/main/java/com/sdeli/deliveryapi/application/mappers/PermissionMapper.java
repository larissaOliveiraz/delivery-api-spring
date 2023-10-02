package com.sdeli.deliveryapi.application.mappers;

import com.sdeli.deliveryapi.application.dto.PermissionDTO;
import com.sdeli.deliveryapi.domain.model.Permission;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class PermissionMapper {

    private final ModelMapper modelMapper;

    public PermissionDTO toDTO(Permission permission) {
        return modelMapper.map(permission, PermissionDTO.class);
    }

    public List<PermissionDTO> toCollectionDTO(Collection<Permission> permissions) {
        return permissions.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
