package com.sdeli.deliveryapi.model;

import lombok.Getter;

@Getter
public enum OrderStatus {

    CREATED,
    CONFIRMED,
    DELIVERED,
    CANCELLED

}
