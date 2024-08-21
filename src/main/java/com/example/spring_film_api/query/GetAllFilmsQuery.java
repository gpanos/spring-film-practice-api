package com.example.spring_film_api.query;

import com.example.spring_film_api.dto.FilmDTO;
import com.example.spring_film_api.mapper.FilmMapper;
import com.example.spring_film_api.model.Film;
import com.example.spring_film_api.repository.FilmRepository;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetAllFilmsQuery {
    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;

    public GetAllFilmsQuery(FilmRepository filmRepository, FilmMapper filmMapper) {
        this.filmRepository = filmRepository;
        this.filmMapper = filmMapper;
    }

    public Page<FilmDTO> execute(Optional<List<String>> genres, Optional<String> title, Pageable pageable) {
        Page<Film> films = filmRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            genres.ifPresent(genreList -> {
                predicates.add(root.get("genre").get("title").in(genreList));
            });
            
            title.ifPresent(t -> {
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("title")), 
                    "%" + t.toLowerCase() + "%"
                ));
            });
            
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);
    
    return films.map(filmMapper::toFilmDTO);
    }
}
