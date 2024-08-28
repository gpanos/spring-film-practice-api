package com.example.spring_film_api.exception;

public class PastScreeningException extends RuntimeException {
    public PastScreeningException(String message) {
        super(message);
    }

    public PastScreeningException(String message, Throwable cause) {
        super(message, cause);
    }
}
