package com.sdeli.deliveryapi.controllers;

import com.sdeli.deliveryapi.dto.ProductDTO;
import com.sdeli.deliveryapi.dto.factories.MakeProductDTO;
import com.sdeli.deliveryapi.model.Product;
import com.sdeli.deliveryapi.model.Restaurant;
import com.sdeli.deliveryapi.services.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurants/{restaurantId}/products")
public class RestaurantProductController {

    private final RestaurantService restaurantService;
    private final MakeProductDTO makeDTO;

    @GetMapping
    public List<ProductDTO> findAll(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findByIdOrThrow(restaurantId);
        List<Product> products = restaurant.getProducts();
        return makeDTO.toCollectionDTO(products);
    }

}
