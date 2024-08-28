package com.example.spring_film_api.action;

import org.springframework.stereotype.Component;
import com.example.spring_film_api.dto.SaveScreeningRequest;
import com.example.spring_film_api.dto.ScreeningDTO;
import com.example.spring_film_api.mapper.ScreeningMapper;
import com.example.spring_film_api.model.Film;
import com.example.spring_film_api.model.Screening;
import com.example.spring_film_api.repository.FilmRepository;
import com.example.spring_film_api.repository.ScreeningRepository;
import jakarta.transaction.Transactional;

@Component
public class CreateFilmScreeningAction {
    private final ScreeningRepository screeningRepository;
    private final ScreeningMapper screeningMapper;
    private final FilmRepository filmRepository;

    public CreateFilmScreeningAction(ScreeningRepository screeningRepository,
            ScreeningMapper screeningMapper, FilmRepository filmRepository) {
        this.screeningRepository = screeningRepository;
        this.filmRepository = filmRepository;
        this.screeningMapper = screeningMapper;
    }

    @Transactional
    public ScreeningDTO execute(SaveScreeningRequest request, Long filmId) {
        Film film = filmRepository.findById(filmId).orElseThrow();

        Screening screening = new Screening();
        screening.setFilm(film);
        screening.setStartTime(request.getStartTime());
        screening.setEndTime(request.getEndTime());
        screening.setAuditorium(request.getAuditorium());
        screening.setTotalSeats(request.getTotalSeats());
        screening.setAvailableSeats(request.getTotalSeats());
        screening.setTicketPrice(request.getTicketPrice());

        Screening savedScreening = screeningRepository.save(screening);

        return screeningMapper.toScreeningDTO(savedScreening);
    }

}
