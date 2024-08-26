package com.example.spring_film_api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SaveReviewRequest {
    @Min(value = 1, message = "Rating must be greater than 0")
    @Max(value = 5, message = "Rating must be less than 6")
    private Integer rating;

    @NotBlank(message = "Comment is required")
    private String comment;
}
