package com.example.spring_film_api.factory;

import com.example.spring_film_api.model.Film;
import net.datafaker.Faker;


public class FilmFactory {

    private static final Faker faker = new Faker();

    public static Film createFilm() {
        Film film = new Film();
        film.setTitle(faker.book().title());
        film.setDescription(faker.lorem().sentence());
        return film;
    }
}
