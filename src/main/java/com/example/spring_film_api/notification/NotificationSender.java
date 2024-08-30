package com.example.spring_film_api.notification;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import com.example.spring_film_api.queue.Queueable;

@Service
public class NotificationSender {
    private ApplicationContext context;

    public NotificationSender(ApplicationContext context) {
        this.context = context;
    }


    public void send(Notifiable notifiable, Notification notification) {
        if (notification instanceof Queueable) {
            queueNotification(notifiable, notification);
            return;
        }

        sendNow(notifiable, notification);
    }

    private void sendNow(Notifiable notifiable, Notification notification) {
        for (String via : notification.via()) {
            sendToNotifiable(notifiable, notification, via);
        }

    }

    private void queueNotification(Notifiable notifiable, Notification notification) {
        //
    }

    private void sendToNotifiable(Notifiable notifiable, Notification notification, String via) {
        Channel channel = context.getBean(via + "Channel", Channel.class);

        channel.send(notifiable, notification);
    }
}
