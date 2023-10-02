package com.sdeli.deliveryapi.domain.services;

import com.sdeli.deliveryapi.domain.exceptions.InvalidCredentialsException;
import com.sdeli.deliveryapi.domain.exceptions.UserNotFoundException;
import com.sdeli.deliveryapi.domain.model.Role;
import com.sdeli.deliveryapi.domain.model.User;
import com.sdeli.deliveryapi.domain.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;
    private final RoleService roleService;

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

    @Transactional
    public void addRole(Long userId, Long roleId) {
        User user = findByIdOrThrow(userId);
        Role role = roleService.findByIdOrThrow(roleId);

        user.addRole(role);
    }

    @Transactional
    public void removeRole(Long userId, Long roleId) {
        User user = findByIdOrThrow(userId);
        Role role = roleService.findByIdOrThrow(roleId);

        user.removeRole(role);
    }

    public User findByIdOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

}
