package com.example.spring_film_api.notification;

public interface Mailable {
    public MailMessage toMail(Notifiable notifiable);
}
