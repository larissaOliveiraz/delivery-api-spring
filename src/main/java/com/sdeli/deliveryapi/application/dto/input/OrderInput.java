package com.sdeli.deliveryapi.application.dto.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderInput {

    @Valid
    @NotNull
    private RestaurantReference restaurant;

    @Valid
    @NotNull
    private PaymentTypeReference paymentType;

    @Valid
    @NotNull
    private AddressInput address;

    @Valid
    @Size(min = 1)
    @NotNull
    private List<OrderItemInput> items;

}
