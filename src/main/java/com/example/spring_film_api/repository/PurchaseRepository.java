package com.example.spring_film_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.spring_film_api.model.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}

