package com.sdeli.deliveryapi.dto.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressInput {

    @NotBlank
    private String zipCode;

    @NotBlank
    private String publicArea;

    @NotBlank
    private String number;

    @NotBlank
    private String neighborhood;

    @Valid
    @NotNull
    private CityReference city;

}
