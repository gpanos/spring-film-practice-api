package com.example.spring_film_api.repository;

import com.example.spring_film_api.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}

