package com.example.spring_film_api.query;

import java.util.List;
import org.springframework.stereotype.Component;
import com.example.spring_film_api.dto.ReviewDTO;
import com.example.spring_film_api.mapper.ReviewMapper;
import com.example.spring_film_api.model.Review;
import com.example.spring_film_api.repository.ReviewRepository;

@Component
public class GetAllFilmReviewsQuery {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public GetAllFilmReviewsQuery(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    public List<ReviewDTO> execute(Long filmId) {
        List<Review> reviews = reviewRepository.findByFilmId(filmId);

        return reviewMapper.toReviewDTOs(reviews);
    }
}
