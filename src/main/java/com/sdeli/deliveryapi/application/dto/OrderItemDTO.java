package com.sdeli.deliveryapi.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemDTO {

    private Long productId;
    private String productName;

    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

}
