package com.sdeli.deliveryapi.controllers;

import com.sdeli.deliveryapi.dto.RestaurantDTO;
import com.sdeli.deliveryapi.dto.factories.MakeRestaurantDTO;
import com.sdeli.deliveryapi.dto.input.RestaurantInput;
import com.sdeli.deliveryapi.model.Restaurant;
import com.sdeli.deliveryapi.repositories.RestaurantRepository;
import com.sdeli.deliveryapi.services.RestaurantService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantRepository repository;
    private final RestaurantService service;
    private final MakeRestaurantDTO makeDTO;

    @GetMapping
    public List<RestaurantDTO> findAll() {
        List<Restaurant> restaurants = repository.findAll();
        return makeDTO.toCollectionDTO(restaurants);
    }

    @GetMapping("/{id}")
    public RestaurantDTO findById(@PathVariable Long id) {
        Restaurant restaurant = service.findByIdOrThrow(id);
        return makeDTO.toDTO(restaurant);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantDTO save(@RequestBody @Valid RestaurantInput restaurantInput) {
        Restaurant restaurant = makeDTO.toDomain(restaurantInput);

        restaurant = service.save(restaurant);

        return makeDTO.toDTO(restaurant);
    }

    @PutMapping("/{id}")
    public RestaurantDTO update(@PathVariable Long id,
                                @RequestBody RestaurantInput restaurantInput) {
        Restaurant restaurant = service.findByIdOrThrow(id);

        makeDTO.copyToDomain(restaurantInput, restaurant);
        restaurant = service.save(restaurant);

        return makeDTO.toDTO(restaurant);
    }

}
