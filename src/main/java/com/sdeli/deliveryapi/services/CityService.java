package com.sdeli.deliveryapi.services;

import com.sdeli.deliveryapi.exceptions.CityNotFoundException;
import com.sdeli.deliveryapi.exceptions.EntityInUseException;
import com.sdeli.deliveryapi.model.City;
import com.sdeli.deliveryapi.model.State;
import com.sdeli.deliveryapi.repositories.CityRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    private final CityRepository repository;
    private final StateService stateService;

    public CityService(CityRepository repository, StateService stateService) {
        this.repository = repository;
        this.stateService = stateService;
    }

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
