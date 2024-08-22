package com.example.spring_film_api.controller;

import com.example.spring_film_api.dto.CreateFilmRequest;
import com.example.spring_film_api.factory.FilmFactory;
import com.example.spring_film_api.model.Film;
import com.example.spring_film_api.model.Genre;
import com.example.spring_film_api.repository.FilmRepository;
import com.example.spring_film_api.repository.GenreRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        filmRepository.deleteAll();
        genreRepository.deleteAll();
    }

    @Test
    @Transactional
    void shouldReturnListOfFilms() throws Exception {
        filmRepository.saveAll(List.of(FilmFactory.createFilm(), FilmFactory.createFilm()));
        mockMvc.perform(get("/films"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.content", hasSize(2))); // Ensure two films are returned
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

    @Test
    @Transactional
    void shouldFilterFilmsByGenre() throws Exception {
        Genre actionGenre = new Genre();
        actionGenre.setTitle("Action");
        genreRepository.save(actionGenre);

        Film actionFilm = FilmFactory.createFilm();
        actionFilm.setGenre(actionGenre);
        filmRepository.save(actionFilm);

        filmRepository.save(FilmFactory.createFilm()); // Film without genre

        mockMvc.perform(get("/films").param("genres", "Action"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.content", hasSize(1)))
            .andExpect(jsonPath("$.content[0].genre.title", is("Action")));
    }

    @Test
    @Transactional
    void shouldFilterFilmsByTitle() throws Exception {
        Film film1 = FilmFactory.createFilm();
        film1.setTitle("The Matrix");
        Film film2 = FilmFactory.createFilm();
        film2.setTitle("Inception");
        filmRepository.saveAll(List.of(film1, film2));

        mockMvc.perform(get("/films").param("title", "matrix"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.content", hasSize(1)))
            .andExpect(jsonPath("$.content[0].title", is("The Matrix")));
    }

    @Test
    @Transactional
    void shouldPaginateResults() throws Exception {
        filmRepository.saveAll(List.of(FilmFactory.createFilm(), FilmFactory.createFilm(), FilmFactory.createFilm()));

        mockMvc.perform(get("/films").param("page", "0").param("size", "2"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.content", hasSize(2)))
            .andExpect(jsonPath("$.totalElements", is(3)))
            .andExpect(jsonPath("$.totalPages", is(2)));
    }

    @Test
    @Transactional
    void shouldCombineFiltersAndPagination() throws Exception {
        Genre actionGenre = new Genre();
        actionGenre.setTitle("Action");
        genreRepository.save(actionGenre);

        Film film1 = FilmFactory.createFilm();
        film1.setTitle("Die Hard");
        film1.setGenre(actionGenre);
        Film film2 = FilmFactory.createFilm();
        film2.setTitle("The Matrix");
        film2.setGenre(actionGenre);
        Film film3 = FilmFactory.createFilm();
        film3.setTitle("Inception");
        film3.setGenre(actionGenre);
        filmRepository.saveAll(List.of(film1, film2, film3));

        mockMvc.perform(get("/films")
                .param("genres", "Action")
                .param("title", "die")
                .param("page", "0")
                .param("size", "1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.content", hasSize(1)))
            .andExpect(jsonPath("$.content[0].title", is("Die Hard")))
            .andExpect(jsonPath("$.totalElements", is(1)));
    }

    @Test
    @Transactional
    void shouldStoreNewFilm() throws Exception {
        Genre genre = new Genre();
        genre.setTitle("Action");
        genre = genreRepository.save(genre);

        CreateFilmRequest request = new CreateFilmRequest();
        request.setTitle("Die Hard");
        request.setDescription("A great action movie");
        request.setGenreId(genre.getId());

        mockMvc.perform(post("/films")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.title").value("Die Hard"))
            .andExpect(jsonPath("$.description").value("A great action movie"))
            .andExpect(jsonPath("$.genre.title").value("Action"));


    }
}
