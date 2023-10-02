package com.sdeli.deliveryapi.application.mappers;

import com.sdeli.deliveryapi.application.dto.UserDTO;
import com.sdeli.deliveryapi.application.dto.input.UserInput;
import com.sdeli.deliveryapi.application.dto.input.UserUpdateInput;
import com.sdeli.deliveryapi.domain.model.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserDTO toDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> toCollectionDTO(Collection<User> users) {
        return users.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public User toDomain(UserInput userInput) {
        return modelMapper.map(userInput, User.class);
    }

    public void copyToDomain(UserUpdateInput userInput, User user) {
        modelMapper.map(userInput, user);
    }
}
