package com.example.spring_film_api.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Film extends BaseEntity {
    private String title;

    private String description;
}

