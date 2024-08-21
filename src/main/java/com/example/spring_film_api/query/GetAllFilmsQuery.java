package com.example.spring_film_api.query;

import com.example.spring_film_api.dto.FilmDTO;
import com.example.spring_film_api.mapper.FilmMapper;
import com.example.spring_film_api.model.Film;
import com.example.spring_film_api.repository.FilmRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllFilmsQuery {
    private final FilmRepository filmRepository;

    public GetAllFilmsQuery(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<FilmDTO> execute() {
        return FilmMapper.INSTANCE.toFilmDTOs((List<Film>) filmRepository.findAll());
    }
}
