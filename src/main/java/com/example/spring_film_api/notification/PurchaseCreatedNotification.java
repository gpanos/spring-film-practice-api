package com.example.spring_film_api.notification;

public class PurchaseCreatedNotification extends Notification implements Mailable {
    private Long purchaseId;

    public PurchaseCreatedNotification(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    @Override
    public String[] via() {
        return new String[] {"mail"};
    }

    public MailMessage toMail(Notifiable notifiable) {
        return MailMessage.builder().from("support@tickets.com").subject("Your ticket purchase")
                .body("test purchase with id: " + purchaseId).build();
    }
}
