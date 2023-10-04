package com.sdeli.deliveryapi.domain.services;

import com.sdeli.deliveryapi.domain.model.Order;
import com.sdeli.deliveryapi.domain.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class OrderStatusService {

    private final OrderService service;
    private final OrderRepository repository;

    @Transactional
    public void confirm(String code) {
        Order order = service.findByCodeOrThrow(code);
        order.confirm();

        repository.save(order); //to throw the event
    }

    @Transactional
    public void deliver(String code) {
        Order order = service.findByCodeOrThrow(code);
        order.deliver();

        repository.save(order);
    }

    @Transactional
    public void cancel(String code) {
        Order order = service.findByCodeOrThrow(code);
        order.cancel();

        repository.save(order);
    }

}
