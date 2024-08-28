package com.example.spring_film_api.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import com.example.spring_film_api.model.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long>, JpaSpecificationExecutor<Film> {
    @EntityGraph(attributePaths = {"genre"})
    @NonNull
    List<Film> findAll();

    @EntityGraph(attributePaths = {"genre", "reviews", "screenings"})
    @NonNull
    Optional<Film> findById(@NonNull Long id);
}
