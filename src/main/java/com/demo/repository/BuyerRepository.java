package com.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.Buyer;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {

	Optional<Buyer> findByEmailAndPassword(String email, String password);
    // Additional query methods can be added if needed
}

