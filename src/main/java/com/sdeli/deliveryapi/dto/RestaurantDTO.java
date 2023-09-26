package com.sdeli.deliveryapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantDTO {

    private Long id;
    private String name;
    private CategoryDTO category;
    private BigDecimal shipment;
    private Boolean active;
    private Boolean open;

    private AddressDTO address;

}
