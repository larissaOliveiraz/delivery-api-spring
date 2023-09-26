package com.sdeli.deliveryapi.controllers;

import com.sdeli.deliveryapi.dto.PaymentTypeDTO;
import com.sdeli.deliveryapi.dto.factories.MakePaymentTypeDTO;
import com.sdeli.deliveryapi.model.PaymentType;
import com.sdeli.deliveryapi.model.Restaurant;
import com.sdeli.deliveryapi.services.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurants/{restaurantId}/payment-types")
public class RestaurantPaymentTypeController {

    private final RestaurantService restaurantService;
    private final MakePaymentTypeDTO makeDTO;

    @GetMapping
    public List<PaymentTypeDTO> findAll(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findByIdOrThrow(restaurantId);
        Set<PaymentType> paymentTypes = restaurant.getPaymentTypes();
        return makeDTO.toCollectionDTO(paymentTypes);
    }

    @PutMapping("/{paymentTypeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addPaymentType(@PathVariable Long restaurantId,
                               @PathVariable Long paymentTypeId) {
        restaurantService.addPaymentType(restaurantId, paymentTypeId);
    }

    @DeleteMapping("/{paymentTypeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePaymentType(@PathVariable Long restaurantId,
                               @PathVariable Long paymentTypeId) {
        restaurantService.removePaymentType(restaurantId, paymentTypeId);
    }

}
