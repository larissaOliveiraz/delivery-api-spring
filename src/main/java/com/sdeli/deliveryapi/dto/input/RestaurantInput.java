package com.sdeli.deliveryapi.dto.input;

import com.sdeli.deliveryapi.dto.AddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantInput {

    @NotBlank
    private String name;

    @NotNull
    @PositiveOrZero
    private BigDecimal shipment;

    @Valid
    @NotNull
    private CategoryReference category;

    @Valid
    @NotNull
    private AddressDTO address;

}
