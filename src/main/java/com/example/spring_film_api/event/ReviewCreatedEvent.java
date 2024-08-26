package com.example.spring_film_api.event;

public class ReviewCreatedEvent {
    private Long reviewId;

    public ReviewCreatedEvent(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Long getReviewId() {
        return reviewId;
    }
}
