package com.sdeli.deliveryapi.controllers;

import com.sdeli.deliveryapi.dto.UserDTO;
import com.sdeli.deliveryapi.dto.factories.MakeUserDTO;
import com.sdeli.deliveryapi.dto.input.UserInput;
import com.sdeli.deliveryapi.dto.input.UserPasswordInput;
import com.sdeli.deliveryapi.dto.input.UserUpdateInput;
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

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Long id) {
        User user = service.findByIdOrThrow(id);
        return makeDTO.toDTO(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO save(@RequestBody @Valid UserInput userInput) {
        User user = makeDTO.toDomain(userInput);

        user = service.save(user);

        return makeDTO.toDTO(user);
    }

    @PutMapping("/{id}")
    public UserDTO update(@PathVariable Long id,
                          @RequestBody @Valid UserUpdateInput userInput) {
        User user = service.findByIdOrThrow(id);

        makeDTO.copyToDomain(userInput, user);
        user = service.save(user);

        return makeDTO.toDTO(user);
    }

    @PutMapping("/{id}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@PathVariable Long id,
                               @RequestBody UserPasswordInput userInput) {
        service.updatePassword(
                id,
                userInput.getCurrentPassword(),
                userInput.getNewPassword()
        );
    }

}
