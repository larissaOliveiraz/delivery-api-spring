package com.sdeli.deliveryapi.application.controllers.order;

import com.sdeli.deliveryapi.application.dto.CityDTO;
import com.sdeli.deliveryapi.application.mappers.CityMapper;
import com.sdeli.deliveryapi.application.dto.input.CityInput;
import com.sdeli.deliveryapi.domain.model.City;
import com.sdeli.deliveryapi.domain.repositories.CityRepository;
import com.sdeli.deliveryapi.domain.services.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityRepository repository;
    private final CityService service;
    private final CityMapper makeDTO;

    @GetMapping
    public List<CityDTO> findAll() {
        List<City> cities = repository.findAll();
        return makeDTO.toCollectionDTO(cities);
    }

    @GetMapping("/{id}")
    public CityDTO findById(@PathVariable Long id) {
        City city = service.findByIdOrThrow(id);
        return makeDTO.toDTO(city);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityDTO save(@RequestBody CityInput cityInput) {
        City city = makeDTO.toDomain(cityInput);

        city = service.save(city);

        return makeDTO.toDTO(city);
    }

    @PutMapping("/{id}")
    public CityDTO update(@PathVariable Long id,
                          @RequestBody CityInput cityInput) {
        City city = service.findByIdOrThrow(id);

        makeDTO.copyToDomain(cityInput, city);

        city = service.save(city);

        return makeDTO.toDTO(city);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
