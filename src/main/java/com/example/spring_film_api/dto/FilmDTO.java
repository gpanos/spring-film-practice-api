package com.example.spring_film_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmDTO {
    private Long id;

    private String title;

    private String description;

    private GenreDTO genre;

    private Double averageRating;
}
