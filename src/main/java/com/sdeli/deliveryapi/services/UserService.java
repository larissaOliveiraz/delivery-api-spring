package com.sdeli.deliveryapi.services;

import com.sdeli.deliveryapi.model.User;
import com.sdeli.deliveryapi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }

}
