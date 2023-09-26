package com.sdeli.deliveryapi.dto.factories;

import com.sdeli.deliveryapi.dto.RestaurantDTO;
import com.sdeli.deliveryapi.dto.input.RestaurantInput;
import com.sdeli.deliveryapi.model.Restaurant;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class MakeRestaurantDTO {

    private final ModelMapper modelMapper;

    public RestaurantDTO toDTO(Restaurant restaurant) {
        return modelMapper.map(restaurant, RestaurantDTO.class);
    }

    public List<RestaurantDTO> toCollectionDTO(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Restaurant toDomain(RestaurantInput restaurantInput) {
        return modelMapper.map(restaurantInput, Restaurant.class);
    }

    public void copyToDomain(RestaurantInput restaurantInput, Restaurant restaurant) {
        modelMapper.map(restaurantInput, restaurant);
    }
}
