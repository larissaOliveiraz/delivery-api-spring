package com.sdeli.deliveryapi.dto;

import com.sdeli.deliveryapi.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class OrderShorterDTO {

    private String code;

    private BigDecimal subtotal;
    private BigDecimal shipment;
    private BigDecimal total;

    private OrderStatus status;

    private OffsetDateTime creationDate;

    private RestaurantReferenceDTO restaurant;
    private UserDTO client;

}
