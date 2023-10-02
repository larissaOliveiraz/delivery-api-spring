package com.sdeli.deliveryapi.application.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentTypeInput {

    @NotBlank
    private String description;

}
