package com.example.spring_film_api.exception;

public class NoSeatsLeftException extends RuntimeException {
    public NoSeatsLeftException(String message) {
        super(message);
    }

    public NoSeatsLeftException(String message, Throwable cause) {
        super(message, cause);
    }
}
