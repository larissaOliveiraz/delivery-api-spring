package com.sdeli.deliveryapi.domain.services;

import com.sdeli.deliveryapi.domain.exceptions.RestaurantNotFoundException;
import com.sdeli.deliveryapi.domain.model.*;
import com.sdeli.deliveryapi.domain.repositories.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository repository;
    private final CategoryService categoryService;
    private final CityService cityService;
    private final PaymentTypeService paymentTypeService;
    private final UserService userService;

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
    public void addPaymentType(Long restaurantId, Long paymentTypeId) {
       Restaurant restaurant = findByIdOrThrow(restaurantId);
       PaymentType paymentType = paymentTypeService.findByIdOrThrow(paymentTypeId);
       restaurant.addPaymentType(paymentType);
    }

    @Transactional
    public void removePaymentType(Long restaurantId, Long paymentTypeId) {
        Restaurant restaurant = findByIdOrThrow(restaurantId);
        PaymentType paymentType = paymentTypeService.findByIdOrThrow(paymentTypeId);
        restaurant.removePaymentType(paymentType);
    }

    @Transactional
    public void addUserResponsible(Long restaurantId, Long userId) {
        Restaurant restaurant = findByIdOrThrow(restaurantId);
        User user = userService.findByIdOrThrow(userId);
        restaurant.addUserResponsible(user);
    }

    @Transactional
    public void removeUserResponsible(Long restaurantId, Long userId) {
        Restaurant restaurant = findByIdOrThrow(restaurantId);
        User user = userService.findByIdOrThrow(userId);
        restaurant.removeUserResponsible(user);
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

    @Transactional
    public void activateMany(List<Long> idList) {
        idList.forEach(this::activate);
    }

    @Transactional
    public void deactivateMany(List<Long> idList) {
        idList.forEach(this::deactivate);
    }

    @Transactional
    public void open(Long id) {
        Restaurant restaurant = findByIdOrThrow(id);
        restaurant.open();
    }

    @Transactional
    public void close(Long id) {
        Restaurant restaurant = findByIdOrThrow(id);
        restaurant.close();
    }

    public Restaurant findByIdOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

}
