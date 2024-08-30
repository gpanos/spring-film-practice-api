package com.example.spring_film_api.notification;

public interface Channel {
    void send(Notifiable notifiable, Notification notification);
}
