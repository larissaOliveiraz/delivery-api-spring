package com.sdeli.deliveryapi.domain.repositories;

import com.sdeli.deliveryapi.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
