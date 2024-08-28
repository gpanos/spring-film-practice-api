package com.example.spring_film_api.dto;

import java.util.List;
import lombok.Data;

@Data
public class PurchaseDTO {
    private Long id;

    private String formattedTotalAmount;

    private List<TicketDTO> tickets;
}

