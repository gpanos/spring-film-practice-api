package com.example.spring_film_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.example.spring_film_api.services.Mailer;
import com.example.spring_film_api.services.MailtrapMailer;

@Configuration
public class MailerConfig {

    @Bean
    @Profile("local")
    public Mailer mailtrapMailer() {
        return new MailtrapMailer();
    }

}
