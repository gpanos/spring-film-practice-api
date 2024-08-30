package com.example.spring_film_api.services;

public class MailtrapMailer implements Mailer {
    private String to;

    private String from;

    private String subject;

    private String body;

    public MailtrapMailer to(String to) {
        this.to = to;
        return this;
    }

    public MailtrapMailer from(String from) {
        this.from = from;
        return this;
    }

    public MailtrapMailer subject(String subject) {
        this.subject = subject;
        return this;
    }

    public MailtrapMailer body(String body) {
        this.body = body;
        return this;
    }

    public void send() {
        System.out.println("Sending email to " + this.to);
        System.out.println("From: " + this.from);
        System.out.println("Subject: " + this.subject);
        System.out.println("Body: " + this.body);
    }
}
