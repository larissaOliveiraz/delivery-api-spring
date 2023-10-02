package com.sdeli.deliveryapi.application.dto;

import com.sdeli.deliveryapi.domain.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderDTO {

    private String code;

    private BigDecimal subtotal;
    private BigDecimal shipment;
    private BigDecimal total;

    private OrderStatus status;

    private OffsetDateTime creationDate;
    private OffsetDateTime confirmationDate;
    private OffsetDateTime deliveryDate;
    private OffsetDateTime cancellationDate;

    private AddressDTO address;

    private PaymentTypeDTO paymentType;
    private RestaurantShorterDTO restaurant;
    private UserShorterDTO client;
    private List<OrderItemDTO> items = new ArrayList<>();

}
