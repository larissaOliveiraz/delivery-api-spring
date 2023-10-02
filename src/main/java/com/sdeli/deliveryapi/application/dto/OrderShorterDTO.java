package com.sdeli.deliveryapi.application.dto;

import com.sdeli.deliveryapi.domain.model.OrderStatus;
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

    private RestaurantShorterDTO restaurant;
    private UserShorterDTO client;

}
