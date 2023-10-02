package com.sdeli.deliveryapi.application.controllers.restaurant;

import com.sdeli.deliveryapi.application.dto.UserDTO;
import com.sdeli.deliveryapi.application.mappers.UserMapper;
import com.sdeli.deliveryapi.domain.model.Restaurant;
import com.sdeli.deliveryapi.domain.model.User;
import com.sdeli.deliveryapi.domain.services.RestaurantService;
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
    private final UserMapper makeDTO;

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
