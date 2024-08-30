package com.example.spring_film_api.services;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


public class MailtrapMailer implements Mailer {
    private final JavaMailSender mailSender;

    public MailtrapMailer(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

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
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setText(body, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
