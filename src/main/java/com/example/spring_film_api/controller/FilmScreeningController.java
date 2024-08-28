package com.example.spring_film_api.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.spring_film_api.action.CreateFilmScreeningAction;
import com.example.spring_film_api.dto.SaveScreeningRequest;
import com.example.spring_film_api.dto.ScreeningDTO;
import com.example.spring_film_api.query.GetAllFilmScreeningsQuery;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/films/{id}/screenings")
public class FilmScreeningController {
    private final GetAllFilmScreeningsQuery getAllFilmScreeningsQuery;
    private final CreateFilmScreeningAction createFilmScreeningAction;

    public FilmScreeningController(GetAllFilmScreeningsQuery getAllFilmScreeningsQuery,
            CreateFilmScreeningAction createFilmScreeningAction) {
        this.getAllFilmScreeningsQuery = getAllFilmScreeningsQuery;
        this.createFilmScreeningAction = createFilmScreeningAction;
    }

    @GetMapping()
    public List<ScreeningDTO> index(@PathVariable Long id) {
        return getAllFilmScreeningsQuery.execute(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ScreeningDTO> store(@Valid @RequestBody SaveScreeningRequest request,
            @PathVariable Long id) {
        ScreeningDTO screening = createFilmScreeningAction.execute(request, id);

        return ResponseEntity.status(HttpStatus.CREATED).body(screening);
    }

}
