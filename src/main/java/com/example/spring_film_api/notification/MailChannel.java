package com.example.spring_film_api.notification;

import org.springframework.stereotype.Component;
import com.example.spring_film_api.services.Mailer;


@Component("mailChannel")
public class MailChannel implements Channel {
    private final Mailer mailer;

    public MailChannel(Mailer mailer) {
        this.mailer = mailer;
    }


    @Override
    public void send(Notifiable notifiable, Notification notification) {
        String email = notifiable.routeNotificationForMail();

        if (!(notification instanceof Mailable)) {
            throw new RuntimeException("Notification must implement the Mailable interface");
        }
        Mailable mailable = (Mailable) notification;

        MailMessage message = mailable.toMail(notifiable);

        mailer.from(message.getFrom()).to(email).subject(message.getSubject())
                .body(message.getBody()).send();
    }
}
