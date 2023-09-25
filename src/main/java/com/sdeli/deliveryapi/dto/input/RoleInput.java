package com.sdeli.deliveryapi.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleInput {

    @NotBlank
    private String name;

}
