package com.example.spring_film_api.model;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Film extends BaseEntity {
    private String title;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
    private List<Review> reviews;

    @Column(name = "average_rating", nullable = false)
    private Double averageRating = 0.0;
}

