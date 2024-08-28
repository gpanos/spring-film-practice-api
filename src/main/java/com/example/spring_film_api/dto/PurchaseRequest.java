package com.example.spring_film_api.dto;

import java.util.List;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PurchaseRequest {
    @NotNull
    private Long screeningId;

    private List<TicketRequestDTO> tickets;
}
