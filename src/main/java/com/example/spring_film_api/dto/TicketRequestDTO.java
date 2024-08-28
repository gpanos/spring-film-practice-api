package com.example.spring_film_api.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TicketRequestDTO {
    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String seatNumber;
}
