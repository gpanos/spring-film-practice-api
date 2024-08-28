package com.example.spring_film_api.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScreeningDTO {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String auditorium;
    private int availableSeats;
    private String formattedTicketPrice;

}
