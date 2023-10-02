package com.sdeli.deliveryapi.domain.services;

import com.sdeli.deliveryapi.domain.exceptions.GeneralBusinessException;
import com.sdeli.deliveryapi.domain.exceptions.OrderNotFoundException;
import com.sdeli.deliveryapi.domain.model.*;
import com.sdeli.deliveryapi.domain.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderService {

    private final OrderRepository repository;
    private final RestaurantService restaurantService;
    private final UserService userService;
    private final PaymentTypeService paymentTypeService;
    private final CityService cityService;
    private final ProductService productService;

    public Order save(Order order) {
        validateOrder(order);
        validateItems(order);

        order.setShipment(order.getRestaurant().getShipment());
        order.calculateOrderTotal();

        return repository.save(order);
    }

    public void validateOrder(Order order) {
        Restaurant restaurant = restaurantService.findByIdOrThrow(order.getRestaurant().getId());
        PaymentType paymentType = paymentTypeService.findByIdOrThrow(order.getPaymentType().getId());
        User client = userService.findByIdOrThrow(order.getClient().getId());
        City city = cityService.findByIdOrThrow(order.getAddress().getCity().getId());

        order.setRestaurant(restaurant);
        order.setPaymentType(paymentType);
        order.setClient(client);
        order.getAddress().setCity(city);

        if (!restaurant.acceptPaymentType(paymentType)) {
            throw new GeneralBusinessException(String.format(
                    "Restaurant with id %d does not accept payment type with id %d",
                    restaurant.getId(), paymentType.getId()
            ));
        }
    }

    public void validateItems(Order order) {
        order.getItems().forEach(item -> {
            Product product = productService.findByIdOrThrow(
                    order.getRestaurant().getId(),
                    item.getProduct().getId()
            );

            item.setOrder(order);
            item.setProduct(product);
            item.setUnitPrice(product.getPrice());
        });
    }

    public Order findByCodeOrThrow(String code) {
        return repository.findByCode(code)
                .orElseThrow(() -> new OrderNotFoundException(code));
    }

}
