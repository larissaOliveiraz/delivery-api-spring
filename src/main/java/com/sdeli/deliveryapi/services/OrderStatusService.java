package com.sdeli.deliveryapi.services;

import com.sdeli.deliveryapi.model.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class OrderStatusService {

    private final OrderService service;

    @Transactional
    public void confirm(String code) {
        Order order = service.findByCodeOrThrow(code);
        order.confirm();
    }

    @Transactional
    public void deliver(String code) {
        Order order = service.findByCodeOrThrow(code);
        order.deliver();
    }

    @Transactional
    public void cancel(String code) {
        Order order = service.findByCodeOrThrow(code);
        order.cancel();
    }

}
