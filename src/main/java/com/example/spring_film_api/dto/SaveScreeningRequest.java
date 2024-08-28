package com.example.spring_film_api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SaveScreeningRequest {
    @NotNull
    @Future
    private LocalDateTime startTime;

    @NotNull
    @Future
    private LocalDateTime endTime;

    @NotNull
    private String auditorium;

    @NotNull
    @Min(1)
    private Integer totalSeats;

    @NotNull
    private BigDecimal ticketPrice;
}
