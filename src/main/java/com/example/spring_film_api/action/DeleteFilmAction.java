package com.example.spring_film_api.action;

import org.springframework.stereotype.Component;

import com.example.spring_film_api.repository.FilmRepository;

import jakarta.transaction.Transactional;

@Component
public class DeleteFilmAction {
    private final FilmRepository filmRepository;

    public DeleteFilmAction(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Transactional
    public void execute(Long id) {
        filmRepository.deleteById(id);
    }
}
