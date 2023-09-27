package com.sdeli.deliveryapi.dto.factories;

import com.sdeli.deliveryapi.dto.OrderDTO;
import com.sdeli.deliveryapi.dto.input.OrderInput;
import com.sdeli.deliveryapi.model.Order;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class MakeOrderDTO {

    private final ModelMapper modelMapper;

    public OrderDTO toDTO(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }

    public List<OrderDTO> toCollectionDTO(List<Order> orders) {
        return orders.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Order toDomain(OrderInput orderInput) {
        return modelMapper.map(orderInput, Order.class);
    }

    public void copyToDomain(OrderInput orderInput, Order order) {
        modelMapper.map(orderInput, order);
    }
}
