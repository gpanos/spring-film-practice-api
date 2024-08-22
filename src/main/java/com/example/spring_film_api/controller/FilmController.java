package com.example.spring_film_api.controller;

import com.example.spring_film_api.action.CreateFilmAction;
import com.example.spring_film_api.dto.CreateFilmRequest;
import com.example.spring_film_api.dto.FilmDTO;
import com.example.spring_film_api.query.GetAllFilmsQuery;
import com.example.spring_film_api.query.GetFilmByIdQuery;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/films")
public class FilmController {

    private final GetAllFilmsQuery getAllFilmsQuery;
    private final GetFilmByIdQuery getFilmByIdQuery;
    private final CreateFilmAction createFilmAction;

    public FilmController(
        GetAllFilmsQuery getAllFilmsQuery, 
        GetFilmByIdQuery getFilmByIdQuery, 
        CreateFilmAction createFilmAction
    ) {
        this.getAllFilmsQuery = getAllFilmsQuery;
        this.getFilmByIdQuery = getFilmByIdQuery;
        this.createFilmAction = createFilmAction;
    }

    @GetMapping
    public Page<FilmDTO> index(
        @RequestParam(required = false, name = "genres") Optional<List<String>> genres,
        @RequestParam(required = false) Optional<String> title,
        @PageableDefault(size = 20) Pageable pageable
    ) {
        return getAllFilmsQuery.execute(genres, title, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmDTO> show(@PathVariable Long id) {
        return getFilmByIdQuery
            .execute(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FilmDTO> store(@Valid @RequestBody CreateFilmRequest request) {
        FilmDTO film = this.createFilmAction.execute(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(film);
    }
    
}
