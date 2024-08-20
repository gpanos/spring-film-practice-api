package com.example.spring_film_api.controller;

import com.example.spring_film_api.model.Film;
import com.example.spring_film_api.query.GetAllFilmsQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {

    private final GetAllFilmsQuery getAllFilmsQuery;

    public FilmController(GetAllFilmsQuery getAllFilmsQuery) {
        this.getAllFilmsQuery = getAllFilmsQuery;
    }

    @GetMapping
    public List<Film> index() {
        return getAllFilmsQuery.execute();
    }
}
