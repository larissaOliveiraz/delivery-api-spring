package com.sdeli.deliveryapi.services;

import com.sdeli.deliveryapi.exceptions.InvalidCredentialsException;
import com.sdeli.deliveryapi.exceptions.UserNotFoundException;
import com.sdeli.deliveryapi.model.User;
import com.sdeli.deliveryapi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }

    @Transactional
    public void updatePassword(Long id,
                               String currentPassword,
                               String newPassword) {
        User user = findByIdOrThrow(id);

        if (!user.getPassword().equals(currentPassword)) {
            throw new InvalidCredentialsException();
        }

        user.setPassword(newPassword);
    }

    public User findByIdOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

}
