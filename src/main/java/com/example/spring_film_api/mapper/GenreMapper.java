package com.example.spring_film_api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.spring_film_api.dto.GenreDTO;
import com.example.spring_film_api.model.Genre;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreMapper INSTANCE = Mappers.getMapper( GenreMapper.class ); 

    GenreDTO toGenreDTO(Genre genre);
}
