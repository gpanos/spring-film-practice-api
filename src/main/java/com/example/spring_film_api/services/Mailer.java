package com.example.spring_film_api.services;

public interface Mailer {
    public Mailer to(String to);

    public Mailer from(String from);

    public Mailer subject(String subject);

    public Mailer body(String body);

    public void send();
}
