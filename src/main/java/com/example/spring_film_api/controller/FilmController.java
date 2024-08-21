package com.example.spring_film_api.controller;

import com.example.spring_film_api.dto.FilmDTO;
import com.example.spring_film_api.model.Film;
import com.example.spring_film_api.query.GetAllFilmsQuery;
import com.example.spring_film_api.query.GetFilmByIdQuery;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/films")
public class FilmController {

    private final GetAllFilmsQuery getAllFilmsQuery;
    private final GetFilmByIdQuery getFilmByIdQuery;

    public FilmController(GetAllFilmsQuery getAllFilmsQuery, GetFilmByIdQuery getFilmByIdQuery) {
        this.getAllFilmsQuery = getAllFilmsQuery;
        this.getFilmByIdQuery = getFilmByIdQuery;
    }

    @GetMapping
    public List<FilmDTO> index() {
        return getAllFilmsQuery.execute();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmDTO> show(@PathVariable Long id) {
        return getFilmByIdQuery
            .execute(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
