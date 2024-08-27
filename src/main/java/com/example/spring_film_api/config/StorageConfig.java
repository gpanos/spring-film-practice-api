package com.example.spring_film_api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.example.spring_film_api.services.LocalStorageService;
import com.example.spring_film_api.services.StorageService;

@Configuration
public class StorageConfig {
    @Value("${app.upload.dir}")
    private String uploadDir;

    @Bean
    @Profile("local")
    public StorageService localStorageService() {
        return new LocalStorageService(uploadDir);
    }
}

