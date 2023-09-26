package com.sdeli.deliveryapi.services;

import com.sdeli.deliveryapi.exceptions.RestaurantNotFoundException;
import com.sdeli.deliveryapi.model.Category;
import com.sdeli.deliveryapi.model.City;
import com.sdeli.deliveryapi.model.Restaurant;
import com.sdeli.deliveryapi.repositories.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository repository;
    private final CategoryService categoryService;
    private final CityService cityService;

    public Restaurant save(Restaurant restaurant) {
        Long categoryId = restaurant.getCategory().getId();
        Long cityId = restaurant.getAddress().getCity().getId();

        Category category = categoryService.findByIdOrThrow(categoryId);
        City city = cityService.findByIdOrThrow(cityId);

        restaurant.setCategory(category);
        restaurant.getAddress().setCity(city);

        return repository.save(restaurant);
    }

    @Transactional
    public void activate(Long id) {
        Restaurant restaurant = findByIdOrThrow(id);

        restaurant.activate();
    }

    @Transactional
    public void deactivate(Long id) {
        Restaurant restaurant = findByIdOrThrow(id);

        restaurant.deactivate();
    }

    public Restaurant findByIdOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

}
