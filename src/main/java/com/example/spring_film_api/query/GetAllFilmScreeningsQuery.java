package com.example.spring_film_api.query;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Component;
import com.example.spring_film_api.dto.ScreeningDTO;
import com.example.spring_film_api.mapper.ScreeningMapper;
import com.example.spring_film_api.model.Screening;
import com.example.spring_film_api.repository.ScreeningRepository;

@Component
public class GetAllFilmScreeningsQuery {
    private final ScreeningRepository screeningRepository;
    private final ScreeningMapper screeningMapper;

    public GetAllFilmScreeningsQuery(ScreeningRepository screeningRepository,
            ScreeningMapper screeningMapper) {
        this.screeningRepository = screeningRepository;
        this.screeningMapper = screeningMapper;
    }

    public List<ScreeningDTO> execute(Long filmId) {
        List<Screening> screenings =
                screeningRepository.findByFilmIdAndStartTimeAfter(filmId, LocalDateTime.now());

        return screeningMapper.toScreeningDTOs(screenings);
    }
}
