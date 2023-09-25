package com.sdeli.deliveryapi.dto.factories;

import com.sdeli.deliveryapi.dto.UserDTO;
import com.sdeli.deliveryapi.dto.input.UserInput;
import com.sdeli.deliveryapi.model.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class MakeUserDTO {

    private final ModelMapper modelMapper;

    public UserDTO toDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> toCollectionDTO(List<User> users) {
        return users.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public User toDomain(UserInput userInput) {
        return modelMapper.map(userInput, User.class);
    }

    public void copyToDomain(UserInput userInput, User user) {
        modelMapper.map(userInput, user);
    }
}
