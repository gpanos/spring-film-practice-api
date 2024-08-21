package com.example.spring_film_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Genre extends BaseEntity {
    private String title;

    private String description;

    @ElementCollection
    private List<String> keywords;

    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Film> films;
}
