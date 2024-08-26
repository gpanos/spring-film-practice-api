package com.example.spring_film_api.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ReviewDTO {
    private Long id;
    private String comment;
    private Integer rating;
    private Long userId;
    private String username;
    private LocalDateTime createdAt;
}
