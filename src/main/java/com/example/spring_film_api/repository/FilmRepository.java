package com.example.spring_film_api.repository;

import com.example.spring_film_api.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
}