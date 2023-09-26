package com.sdeli.deliveryapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {

    private String zipCode;
    private String publicArea;
    private String number;
    private String neighborhood;

    private CityShorterDTO city;

}
