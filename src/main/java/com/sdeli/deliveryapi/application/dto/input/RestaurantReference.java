package com.sdeli.deliveryapi.application.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantReference {

    @NotNull
    private Long id;

}
