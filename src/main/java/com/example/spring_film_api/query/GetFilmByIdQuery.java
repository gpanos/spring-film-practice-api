package com.example.spring_film_api.query;

import com.example.spring_film_api.dto.FilmDTO;
import com.example.spring_film_api.mapper.FilmMapper;
import com.example.spring_film_api.model.Film;
import com.example.spring_film_api.repository.FilmRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GetFilmByIdQuery {
    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;

    public GetFilmByIdQuery(FilmRepository filmRepository, FilmMapper filmMapper) {
        this.filmRepository = filmRepository;
        this.filmMapper = filmMapper;
    }

    public Optional<FilmDTO> execute(Long id) {
        Optional<Film> filmOptional = filmRepository.findById(id);

        return filmOptional.map(filmMapper::toFilmDTO);
    }
}
