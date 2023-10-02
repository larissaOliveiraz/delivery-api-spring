package com.sdeli.deliveryapi.application.dto.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityInput {

    @NotBlank
    private String name;

    @Valid
    @NotNull
    private StateReference state;

}
