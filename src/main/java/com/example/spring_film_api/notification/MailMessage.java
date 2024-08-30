package com.example.spring_film_api.notification;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MailMessage {
    private String from;

    private String subject;

    private String body;
}
