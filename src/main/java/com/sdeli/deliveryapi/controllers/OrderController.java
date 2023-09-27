package com.sdeli.deliveryapi.controllers;

import com.sdeli.deliveryapi.dto.OrderDTO;
import com.sdeli.deliveryapi.dto.OrderShorterDTO;
import com.sdeli.deliveryapi.dto.factories.MakeOrderDTO;
import com.sdeli.deliveryapi.model.Order;
import com.sdeli.deliveryapi.repositories.OrderRepository;
import com.sdeli.deliveryapi.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository repository;
    private final OrderService service;
    private final MakeOrderDTO makeDTO;

    @GetMapping
    public List<OrderShorterDTO> findAll() {
        List<Order> orders = repository.findAll();
        return makeDTO.toShortCollectionDTO(orders);
    }

    @GetMapping("/{code}")
    public OrderDTO findByCode(@PathVariable String code) {
        Order order = service.findByCodeOrThrow(code);
        return makeDTO.toDTO(order);
    }

}
