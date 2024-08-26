package com.example.spring_film_api.action;

import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Component;
import com.example.spring_film_api.model.Film;
import com.example.spring_film_api.model.Review;
import com.example.spring_film_api.repository.FilmRepository;
import com.example.spring_film_api.repository.ReviewRepository;
import jakarta.transaction.Transactional;

@Component
public class UpdateFilmAverageRatingAction {
    private FilmRepository filmRepository;
    private ReviewRepository reviewRepository;

    public UpdateFilmAverageRatingAction(FilmRepository filmRepository,
            ReviewRepository reviewRepository) {
        this.filmRepository = filmRepository;
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public void execute(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow();
        Film film = review.getFilm();

        double averageRating =
                film.getReviews().stream().mapToDouble(Review::getRating).average().orElse(0);

        film.setAverageRating(Precision.round(averageRating, 1));

        filmRepository.save(film);
    }
}
