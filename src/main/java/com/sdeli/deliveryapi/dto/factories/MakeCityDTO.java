package com.sdeli.deliveryapi.dto.factories;

import com.sdeli.deliveryapi.dto.CityDTO;
import com.sdeli.deliveryapi.dto.input.CityInput;
import com.sdeli.deliveryapi.model.City;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MakeCityDTO {

    private final ModelMapper modelMapper;

    public MakeCityDTO(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CityDTO toDTO(City city) {
        return modelMapper.map(city, CityDTO.class);
    }

    public List<CityDTO> toCollectionDTO(List<City> cities) {
        return cities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public City toDomain(CityInput cityInput) {
        return modelMapper.map(cityInput, City.class);
    }

}
