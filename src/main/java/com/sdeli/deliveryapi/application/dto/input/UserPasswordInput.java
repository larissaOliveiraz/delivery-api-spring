package com.sdeli.deliveryapi.application.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPasswordInput {

    @NotBlank
    private String currentPassword;

    @NotBlank
    private String newPassword;

}

