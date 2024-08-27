package com.example.spring_film_api.action;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.example.spring_film_api.model.Film;
import com.example.spring_film_api.repository.FilmRepository;
import com.example.spring_film_api.services.StorageService;

@Component
public class StoreFilmCoverImageAction {
    private StorageService storageService;
    private FilmRepository filmRepository;

    public StoreFilmCoverImageAction(StorageService storageService, FilmRepository filmRepository) {
        this.storageService = storageService;
        this.filmRepository = filmRepository;
    }

    public void execute(Long filmId, MultipartFile file) {
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new RuntimeException("Film not found"));

        try {
            String filePath = storageService.store(file);
            film.setCoverImage(filePath);
            filmRepository.save(film);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
