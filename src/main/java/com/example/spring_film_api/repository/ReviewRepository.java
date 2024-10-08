package com.example.spring_film_api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.spring_film_api.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByFilmId(Long filmId);

    List<Review> findByUserId(Long userId);

}
