package com.sdeli.deliveryapi.domain.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum OrderStatus {

    CREATED("Created"),
    CONFIRMED("Confirmed", CREATED),
    DELIVERED("Delivered", CONFIRMED),
    CANCELLED("Cancelled", CREATED, CONFIRMED);

    private final String title;
    private final List<OrderStatus> statusNeeded;

    OrderStatus(String title, OrderStatus... statusNeeded) {
        this.title = title;
        this.statusNeeded = Arrays.asList(statusNeeded);
    }

    public boolean statusNotAllowed(OrderStatus orderStatus) {
        return !orderStatus.statusNeeded.contains(this);
    }

}
