package com.sdeli.deliveryapi.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentTypeReference {

    @NotNull
    private Long id;

}
