package com.sdeli.deliveryapi.controllers;

import com.sdeli.deliveryapi.dto.CityDTO;
import com.sdeli.deliveryapi.dto.factories.MakeCityDTO;
import com.sdeli.deliveryapi.dto.input.CityInput;
import com.sdeli.deliveryapi.model.City;
import com.sdeli.deliveryapi.repositories.CityRepository;
import com.sdeli.deliveryapi.services.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityRepository repository;
    private final CityService service;
    private final MakeCityDTO makeDTO;

    public CityController(CityRepository repository, CityService service, MakeCityDTO makeDTO) {
        this.repository = repository;
        this.service = service;
        this.makeDTO = makeDTO;
    }

    @GetMapping
    public List<CityDTO> findAll() {
        List<City> cities = repository.findAll();
        return makeDTO.toCollectionDTO(cities);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityDTO save(@RequestBody CityInput cityInput) {
        City city = makeDTO.toDomain(cityInput);

        city = service.save(city);

        return makeDTO.toDTO(city);
    }

}
