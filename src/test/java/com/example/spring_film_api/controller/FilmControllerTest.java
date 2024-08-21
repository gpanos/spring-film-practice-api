package com.example.spring_film_api.controller;

import com.example.spring_film_api.factory.FilmFactory;
import com.example.spring_film_api.model.Film;
import com.example.spring_film_api.repository.FilmRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FilmRepository filmRepository;

    @Test
    @Transactional
    void shouldReturnListOfFilms() throws Exception {
        filmRepository.saveAll(List.of(FilmFactory.createFilm(), FilmFactory.createFilm()));

        mockMvc.perform(get("/films"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$", hasSize(2))); // Ensure two films are returned
    }

    @Test
    @Transactional
    void shouldReturnTheDetailsOfFilm() throws Exception {
        Film film = filmRepository.save(FilmFactory.createFilm());

        mockMvc.perform(get("/films/" + film.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.id", is(film.getId().intValue())))
            .andExpect(jsonPath("$.title", is(film.getTitle())))
            .andExpect(jsonPath("$.description", is(film.getDescription())));
    }
}
