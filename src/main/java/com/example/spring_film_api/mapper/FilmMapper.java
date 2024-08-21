package com.example.spring_film_api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.spring_film_api.dto.FilmDTO;
import com.example.spring_film_api.model.Film;

@Mapper(componentModel = "spring")
public interface FilmMapper {
    FilmMapper INSTANCE = Mappers.getMapper( FilmMapper.class ); 

    @Mapping(source = "genre", target = "genre")
    Film toFilm(FilmDTO filmDTO);

    FilmDTO toFilmDTO(Film film);

    List<FilmDTO> toFilmDTOs(List<Film> films);
}
