package com.example.spring_film_api.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.spring_film_api.action.CreateFilmReviewAction;
import com.example.spring_film_api.dto.ReviewDTO;
import com.example.spring_film_api.dto.SaveReviewRequest;
import com.example.spring_film_api.model.User;
import com.example.spring_film_api.query.GetAllFilmReviewsQuery;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/films/{id}/reviews")
public class FilmReviewController {
    private final GetAllFilmReviewsQuery getAllFilmReviewsQuery;
    private final CreateFilmReviewAction createFilmReviewAction;

    public FilmReviewController(GetAllFilmReviewsQuery getAllFilmReviewsQuery,
            CreateFilmReviewAction createFilmReviewAction) {
        this.getAllFilmReviewsQuery = getAllFilmReviewsQuery;
        this.createFilmReviewAction = createFilmReviewAction;
    }

    @GetMapping()
    public List<ReviewDTO> index(@PathVariable Long id) {

        return getAllFilmReviewsQuery.execute(id);
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> store(@Valid @RequestBody SaveReviewRequest request,
            @PathVariable Long id, @AuthenticationPrincipal User user) {
        ReviewDTO review = createFilmReviewAction.execute(request, id, user.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(review);
    }

}
