package com.example.spring_film_api.action;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import com.example.spring_film_api.dto.ReviewDTO;
import com.example.spring_film_api.dto.SaveReviewRequest;
import com.example.spring_film_api.event.ReviewCreatedEvent;
import com.example.spring_film_api.mapper.ReviewMapper;
import com.example.spring_film_api.model.Film;
import com.example.spring_film_api.model.Review;
import com.example.spring_film_api.model.User;
import com.example.spring_film_api.repository.FilmRepository;
import com.example.spring_film_api.repository.ReviewRepository;
import com.example.spring_film_api.repository.UserRepository;
import jakarta.transaction.Transactional;

@Component
public class CreateFilmReviewAction {
    private ReviewRepository reviewRepository;
    private FilmRepository filmRepository;
    private UserRepository userRepository;
    private ReviewMapper reviewMapper;
    private ApplicationEventPublisher eventPublisher;

    public CreateFilmReviewAction(ReviewRepository reviewRepository, FilmRepository filmRepository,
            UserRepository userRepository, ReviewMapper reviewMapper,
            ApplicationEventPublisher eventPublisher) {
        this.reviewRepository = reviewRepository;
        this.filmRepository = filmRepository;
        this.reviewMapper = reviewMapper;
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
    }



    @Transactional
    public ReviewDTO execute(SaveReviewRequest request, Long filmId, Long userId) {
        Film film = filmRepository.findById(filmId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();

        Review review = new Review();
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setFilm(film);
        review.setUser(user);

        Review savedReview = reviewRepository.save(review);

        eventPublisher.publishEvent(new ReviewCreatedEvent(savedReview.getId()));

        return reviewMapper.toReviewDTO(savedReview);
    }

}
