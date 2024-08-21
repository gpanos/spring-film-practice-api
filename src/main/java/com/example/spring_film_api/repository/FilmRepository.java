package com.example.spring_film_api.repository;

import com.example.spring_film_api.model.Film;

import org.springframework.data.jpa.repository.EntityGraph;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;

public interface FilmRepository extends JpaRepository<Film, Long>, JpaSpecificationExecutor<Film> {
    @EntityGraph(attributePaths = {"genre"})
    @NonNull
    List<Film> findAll();

    @EntityGraph(attributePaths = {"genre"})
    @NonNull
    Optional<Film> findById(@NonNull Long id);
}