package com.sdeli.deliveryapi.application.controllers.order;

import com.sdeli.deliveryapi.application.dto.OrderDTO;
import com.sdeli.deliveryapi.application.dto.OrderShorterDTO;
import com.sdeli.deliveryapi.application.mappers.OrderMapper;
import com.sdeli.deliveryapi.application.dto.input.OrderInput;
import com.sdeli.deliveryapi.domain.exceptions.EntityNotFoundException;
import com.sdeli.deliveryapi.domain.exceptions.GeneralBusinessException;
import com.sdeli.deliveryapi.domain.model.Order;
import com.sdeli.deliveryapi.domain.model.User;
import com.sdeli.deliveryapi.domain.repositories.OrderRepository;
import com.sdeli.deliveryapi.domain.services.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository repository;
    private final OrderService service;
    private final OrderMapper makeDTO;

    @GetMapping
    public Page<OrderShorterDTO> findAll(Pageable pageable) {
        Page<Order> ordersPage = repository.findAll(pageable);

        List<OrderShorterDTO> orders = makeDTO.toShortCollectionDTO(ordersPage.getContent());

        return new PageImpl<>(orders, pageable, ordersPage.getTotalElements());
    }

    @GetMapping("/{code}")
    public OrderDTO findByCode(@PathVariable String code) {
        Order order = service.findByCodeOrThrow(code);
        return makeDTO.toDTO(order);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO save(@RequestBody @Valid OrderInput orderInput) {
        try {
            Order order = makeDTO.toDomain(orderInput);

            order.setClient(new User());
            order.getClient().setId(1L);

            order = service.save(order);
            return makeDTO.toDTO(order);
        } catch (EntityNotFoundException ex) {
            throw new GeneralBusinessException(ex.getMessage());
        }
    }

}
