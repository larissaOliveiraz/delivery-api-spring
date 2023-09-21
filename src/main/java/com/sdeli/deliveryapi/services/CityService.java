package com.sdeli.deliveryapi.services;

import com.sdeli.deliveryapi.model.City;
import com.sdeli.deliveryapi.model.State;
import com.sdeli.deliveryapi.repositories.CityRepository;
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
}
