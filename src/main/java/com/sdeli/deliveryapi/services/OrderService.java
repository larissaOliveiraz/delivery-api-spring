package com.sdeli.deliveryapi.services;

import com.sdeli.deliveryapi.exceptions.OrderNotFoundException;
import com.sdeli.deliveryapi.model.Order;
import com.sdeli.deliveryapi.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderService {

    private final OrderRepository repository;

    public Order findByCodeOrThrow(String code) {
        return repository.findByCode(code)
                .orElseThrow(() -> new OrderNotFoundException(code));
    }

}
