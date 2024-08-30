package com.example.spring_film_api.notification;

import java.io.Serializable;

public abstract class Notification implements Serializable {
    public abstract String[] via();
}
