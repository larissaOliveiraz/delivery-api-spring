package com.sdeli.deliveryapi.controllers;

import com.sdeli.deliveryapi.dto.UserDTO;
import com.sdeli.deliveryapi.dto.factories.MakeUserDTO;
import com.sdeli.deliveryapi.dto.input.UserInput;
import com.sdeli.deliveryapi.model.User;
import com.sdeli.deliveryapi.repositories.UserRepository;
import com.sdeli.deliveryapi.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository repository;
    private final UserService service;
    private final MakeUserDTO makeDTO;

    @GetMapping
    public List<UserDTO> findAll() {
        List<User> users = repository.findAll();
        return makeDTO.toCollectionDTO(users);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO save(@RequestBody @Valid UserInput userInput) {
        User user = makeDTO.toDomain(userInput);

        user = service.save(user);

        return makeDTO.toDTO(user);
    }

}
