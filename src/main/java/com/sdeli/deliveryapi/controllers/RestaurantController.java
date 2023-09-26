package com.sdeli.deliveryapi.controllers;

import com.sdeli.deliveryapi.dto.RestaurantDTO;
import com.sdeli.deliveryapi.dto.factories.MakeRestaurantDTO;
import com.sdeli.deliveryapi.model.Restaurant;
import com.sdeli.deliveryapi.repositories.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantRepository repository;
    private final MakeRestaurantDTO makeDTO;

    @GetMapping
    public List<RestaurantDTO> findAll() {
        List<Restaurant> restaurants = repository.findAll();
        return makeDTO.toCollectionDTO(restaurants);
    }

}
