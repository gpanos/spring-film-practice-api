package com.example.spring_film_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.spring_film_api.action.StoreFilmCoverImageAction;


@RestController
@RequestMapping("films/{id}/cover-image")
public class FilmCoverImageController {
    private StoreFilmCoverImageAction storeFilmCoverImageAction;

    public FilmCoverImageController(StoreFilmCoverImageAction storeFilmCoverImageAction) {
        this.storeFilmCoverImageAction = storeFilmCoverImageAction;
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> store(@PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        this.storeFilmCoverImageAction.execute(id, file);

        return ResponseEntity.noContent().build();
    }
}
