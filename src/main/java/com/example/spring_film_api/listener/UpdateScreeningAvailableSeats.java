package com.example.spring_film_api.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import com.example.spring_film_api.action.UpdateScreeningAvailableSeatsAction;
import com.example.spring_film_api.event.PurchaseCreatedEvent;

@Component
public class UpdateScreeningAvailableSeats {
    private final UpdateScreeningAvailableSeatsAction updateScreeningAvailableSeatsAction;

    public UpdateScreeningAvailableSeats(
            UpdateScreeningAvailableSeatsAction updateScreeningAvailableSeatsAction) {
        this.updateScreeningAvailableSeatsAction = updateScreeningAvailableSeatsAction;
    }

    @EventListener
    public void handlePurchaseCreatedEvent(PurchaseCreatedEvent event) {
        updateScreeningAvailableSeatsAction.execute(event.getPurchaseId());
    }
}
