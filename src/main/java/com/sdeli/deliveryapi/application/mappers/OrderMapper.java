package com.sdeli.deliveryapi.application.mappers;

import com.sdeli.deliveryapi.application.dto.OrderDTO;
import com.sdeli.deliveryapi.application.dto.input.OrderInput;
import com.sdeli.deliveryapi.application.dto.OrderShorterDTO;
import com.sdeli.deliveryapi.domain.model.Order;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class OrderMapper {

    private final ModelMapper modelMapper;

    public OrderDTO toDTO(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }

    public List<OrderDTO> toCollectionDTO(List<Order> orders) {
        return orders.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public OrderShorterDTO toShortDTO(Order order) {
        return modelMapper.map(order, OrderShorterDTO.class);
    }

    public List<OrderShorterDTO> toShortCollectionDTO(List<Order> orders) {
        return orders.stream()
                .map(this::toShortDTO)
                .collect(Collectors.toList());
    }

    public Order toDomain(OrderInput orderInput) {
        return modelMapper.map(orderInput, Order.class);
    }

    public void copyToDomain(OrderInput orderInput, Order order) {
        modelMapper.map(orderInput, order);
    }
}
