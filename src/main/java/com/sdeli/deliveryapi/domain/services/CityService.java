package com.sdeli.deliveryapi.domain.services;

import com.sdeli.deliveryapi.domain.exceptions.CityNotFoundException;
import com.sdeli.deliveryapi.domain.exceptions.EntityInUseException;
import com.sdeli.deliveryapi.domain.model.City;
import com.sdeli.deliveryapi.domain.model.State;
import com.sdeli.deliveryapi.domain.repositories.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CityService {

    private final CityRepository repository;
    private final StateService stateService;

    public City save(City city) {
        State state = stateService.findByIdOrThrow(city.getState().getId());

        city.setState(state);

        return repository.save(city);
    }

    public void delete(Long id) {
        try {
            findByIdOrThrow(id);
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException("City", id);
        }
    }

    public City findByIdOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
    }
}
