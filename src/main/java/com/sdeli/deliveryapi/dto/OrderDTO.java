package com.sdeli.deliveryapi.dto;

import com.sdeli.deliveryapi.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class OrderDTO {

    private String code;

    private BigDecimal subtotal;
    private BigDecimal shippingPrice;
    private BigDecimal totalPrice;

    private OrderStatus status;

    private OffsetDateTime creationDate;
    private OffsetDateTime confirmationDate;
    private OffsetDateTime cancellationDate;
    private OffsetDateTime deliveryDate;

    private AddressDTO address;

    private PaymentTypeDTO paymentType;
    private RestaurantReferenceDTO restaurant;
    private UserDTO client;
//    private List<ItemOrderDTO> items = new ArrayList<>();

}
