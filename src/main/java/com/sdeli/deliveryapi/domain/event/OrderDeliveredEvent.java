package com.sdeli.deliveryapi.domain.event;

import com.sdeli.deliveryapi.domain.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderDeliveredEvent {

    private Order order;

}
