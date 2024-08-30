package com.example.spring_film_api.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import com.example.spring_film_api.event.PurchaseCreatedEvent;
import com.example.spring_film_api.model.Purchase;
import com.example.spring_film_api.model.User;
import com.example.spring_film_api.notification.NotificationSender;
import com.example.spring_film_api.notification.PurchaseCreatedNotification;
import com.example.spring_film_api.repository.PurchaseRepository;

@Component
public class SendPurchaseCreatedNotification {
    private final NotificationSender notificationSender;
    private final PurchaseRepository purchaseRepository;

    public SendPurchaseCreatedNotification(NotificationSender notificationSender,
            PurchaseRepository purchaseRepository) {
        this.notificationSender = notificationSender;
        this.purchaseRepository = purchaseRepository;
    }

    @EventListener
    public void handlePurchaseCreatedEvent(PurchaseCreatedEvent event) {
        Purchase purchase = purchaseRepository.findById(event.getPurchaseId())
                .orElseThrow(() -> new RuntimeException("Purchase not found"));

        User user = purchase.getUser();

        notificationSender.send(user, new PurchaseCreatedNotification(purchase.getId()));

    }
}
