package com.sdeli.deliveryapi.services;

import com.sdeli.deliveryapi.exceptions.RestaurantNotFoundException;
import com.sdeli.deliveryapi.model.Category;
import com.sdeli.deliveryapi.model.City;
import com.sdeli.deliveryapi.model.PaymentType;
import com.sdeli.deliveryapi.model.Restaurant;
import com.sdeli.deliveryapi.repositories.RestaurantRepository;
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
