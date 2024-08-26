package com.example.spring_film_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.spring_film_api.model.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByFilmId(Long filmId);

    List<Review> findByUserId(Long userId);

}
