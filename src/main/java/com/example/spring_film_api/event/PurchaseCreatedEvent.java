package com.example.spring_film_api.event;

import lombok.Getter;

@Getter
public class PurchaseCreatedEvent {
    private Long purchaseId;

    public PurchaseCreatedEvent(Long purchaseId) {
        this.purchaseId = purchaseId;
    }
}
