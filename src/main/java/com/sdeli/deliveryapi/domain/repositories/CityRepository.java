package com.sdeli.deliveryapi.domain.repositories;

import com.sdeli.deliveryapi.domain.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
