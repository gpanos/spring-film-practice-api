package com.example.spring_film_api.action;

import org.springframework.stereotype.Component;

import com.example.spring_film_api.dto.CreateFilmRequest;
import com.example.spring_film_api.dto.FilmDTO;
import com.example.spring_film_api.mapper.FilmMapper;
import com.example.spring_film_api.model.Film;
import com.example.spring_film_api.model.Genre;
import com.example.spring_film_api.repository.FilmRepository;
import com.example.spring_film_api.repository.GenreRepository;

import jakarta.transaction.Transactional;

@Component
public class CreateFilmAction {
    private final FilmRepository filmRepository;
    private final GenreRepository genreRepository;
    private final FilmMapper filmMapper;

    public CreateFilmAction(FilmRepository filmRepository, GenreRepository genreRepository, FilmMapper filmMapper) {
        this.filmRepository = filmRepository;
        this.genreRepository = genreRepository;
        this.filmMapper = filmMapper;
    }

    
    @Transactional
    public FilmDTO execute(CreateFilmRequest request) {
        Genre genre = genreRepository.findById(request.getGenreId())
            .orElseThrow(() -> new RuntimeException("Genre not found"));

        Film film = new Film();
        film.setTitle(request.getTitle());
        film.setGenre(genre);
        film.setDescription(request.getDescription());

        Film savedFilm = filmRepository.save(film);

        return filmMapper.toFilmDTO(savedFilm);
    }

}
