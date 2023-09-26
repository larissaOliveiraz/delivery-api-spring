package com.sdeli.deliveryapi.controllers;

import com.sdeli.deliveryapi.dto.UserDTO;
import com.sdeli.deliveryapi.dto.factories.MakeUserDTO;
import com.sdeli.deliveryapi.model.Restaurant;
import com.sdeli.deliveryapi.model.User;
import com.sdeli.deliveryapi.services.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurants/{restaurantId}/users")
public class RestaurantUsersController {

    private final RestaurantService restaurantService;
    private final MakeUserDTO makeDTO;

    @GetMapping
    public List<UserDTO> findAll(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findByIdOrThrow(restaurantId);
        Set<User> users = restaurant.getUsers();
        return makeDTO.toCollectionDTO(users);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addUserResponsible(@PathVariable Long restaurantId,
                                   @PathVariable Long userId) {
        restaurantService.addUserResponsible(restaurantId, userId);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeUserResponsible(@PathVariable Long restaurantId,
                                      @PathVariable Long userId) {
        restaurantService.removeUserResponsible(restaurantId, userId);
    }

}
