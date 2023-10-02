package com.sdeli.deliveryapi.application.mappers;

import com.sdeli.deliveryapi.application.dto.StateDTO;
import com.sdeli.deliveryapi.application.dto.input.StateInput;
import com.sdeli.deliveryapi.domain.model.State;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StateMapper {

    private final ModelMapper modelMapper;

    public StateMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public StateDTO toDTO(State state) {
        return modelMapper.map(state, StateDTO.class);
    }

    public List<StateDTO> toCollectionDTO(List<State> states) {
        return states.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public State toDomain(StateInput stateInput) {
        return modelMapper.map(stateInput, State.class);
    }

    public void copyToDomain(StateInput stateInput, State state) {
        modelMapper.map(stateInput, state);
    }
}
