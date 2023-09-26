package com.sdeli.deliveryapi.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Embeddable
public class Address {

    private String zipCode;
    private String publicArea;
    private String number;
    private String neighborhood;

    @ManyToOne
    private City city;

}
