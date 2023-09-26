package com.sdeli.deliveryapi.controllers;

import com.sdeli.deliveryapi.dto.ProductDTO;
import com.sdeli.deliveryapi.dto.factories.MakeProductDTO;
import com.sdeli.deliveryapi.dto.input.ProductInput;
import com.sdeli.deliveryapi.model.Product;
import com.sdeli.deliveryapi.model.Restaurant;
import com.sdeli.deliveryapi.services.ProductService;
import com.sdeli.deliveryapi.services.RestaurantService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurants/{restaurantId}/products")
public class RestaurantProductController {

    private final RestaurantService restaurantService;
    private final ProductService productService;
    private final MakeProductDTO makeDTO;

    @GetMapping
    public List<ProductDTO> findAll(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findByIdOrThrow(restaurantId);
        List<Product> products = restaurant.getProducts();
        return makeDTO.toCollectionDTO(products);
    }

    @GetMapping("/{productId}")
    public ProductDTO findById(@PathVariable Long restaurantId,
                               @PathVariable Long productId) {
        Product product = productService.findByIdOrThrow(restaurantId, productId);
        return makeDTO.toDTO(product);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO save(@PathVariable Long restaurantId,
                           @RequestBody @Valid ProductInput productInput) {
        Restaurant restaurant = restaurantService.findByIdOrThrow(restaurantId);
        Product product = makeDTO.toDomain(productInput);

        product.setRestaurant(restaurant);

        product = productService.save(product);

        return makeDTO.toDTO(product);
    }

    @PutMapping("/{productId}")
    public ProductDTO update(@PathVariable Long restaurantId,
                             @PathVariable Long productId,
                             @RequestBody @Valid ProductInput productInput) {
        Product product = productService.findByIdOrThrow(restaurantId, productId);

        makeDTO.copyToDomain(productInput, product);
        product = productService.save(product);

        return makeDTO.toDTO(product);
    }

}
