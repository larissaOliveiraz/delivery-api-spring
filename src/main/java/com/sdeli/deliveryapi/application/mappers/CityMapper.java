package com.sdeli.deliveryapi.application.mappers;

import com.sdeli.deliveryapi.application.dto.CityDTO;
import com.sdeli.deliveryapi.application.dto.input.CityInput;
import com.sdeli.deliveryapi.domain.model.City;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityMapper {

    private final ModelMapper modelMapper;

    public CityMapper(ModelMapper modelMapper) {
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

    public void copyToDomain(CityInput cityInput, City city) {
        modelMapper.map(cityInput, city);
    }

}
