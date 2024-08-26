package com.example.spring_film_api.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import com.example.spring_film_api.action.UpdateFilmAverageRatingAction;
import com.example.spring_film_api.event.ReviewCreatedEvent;

@Component
public class UpdateFilmAverageRating {
    private final UpdateFilmAverageRatingAction updateFilmAverageRatingAction;

    public UpdateFilmAverageRating(UpdateFilmAverageRatingAction updateFilmAverageRatingAction) {
        this.updateFilmAverageRatingAction = updateFilmAverageRatingAction;
    }

    @EventListener
    public void handleReviewCreatedEvent(ReviewCreatedEvent event) {
        updateFilmAverageRatingAction.execute(event.getReviewId());
    }
}
