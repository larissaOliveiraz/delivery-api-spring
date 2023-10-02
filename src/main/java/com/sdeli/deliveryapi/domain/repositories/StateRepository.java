package com.sdeli.deliveryapi.domain.repositories;

import com.sdeli.deliveryapi.domain.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
}
