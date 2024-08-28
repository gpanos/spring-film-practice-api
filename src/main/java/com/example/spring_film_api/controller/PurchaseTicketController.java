package com.example.spring_film_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.spring_film_api.action.PurchaseTicketAction;
import com.example.spring_film_api.dto.PurchaseDTO;
import com.example.spring_film_api.dto.PurchaseRequest;
import com.example.spring_film_api.model.User;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/screenings/{id}/purchase")
public class PurchaseTicketController {
    private final PurchaseTicketAction purchaseTicketAction;

    public PurchaseTicketController(PurchaseTicketAction purchaseTicketAction) {
        this.purchaseTicketAction = purchaseTicketAction;
    }

    @PostMapping
    public ResponseEntity<PurchaseDTO> store(@Valid @RequestBody PurchaseRequest request,
            @PathVariable Long id, @AuthenticationPrincipal User user) {

        PurchaseDTO purchase = purchaseTicketAction.execute(request, id, user.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(purchase);
    }
}
