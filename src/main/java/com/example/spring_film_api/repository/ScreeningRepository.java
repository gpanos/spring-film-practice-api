package com.example.spring_film_api.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.spring_film_api.model.Screening;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {
    List<Screening> findByFilmIdAndStartTimeAfter(Long filmId, LocalDateTime startTime);
}
