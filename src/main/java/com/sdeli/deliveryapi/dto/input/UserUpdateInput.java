package com.sdeli.deliveryapi.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateInput {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

}

