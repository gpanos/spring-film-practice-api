package com.example.spring_film_api.action;

import org.springframework.stereotype.Component;
import com.example.spring_film_api.model.Purchase;
import com.example.spring_film_api.model.Screening;
import com.example.spring_film_api.repository.PurchaseRepository;
import com.example.spring_film_api.repository.ScreeningRepository;
import jakarta.transaction.Transactional;

@Component
public class UpdateScreeningAvailableSeatsAction {
    private ScreeningRepository screeningRepository;
    private PurchaseRepository purchaseRepository;

    public UpdateScreeningAvailableSeatsAction(ScreeningRepository screeningRepository,
            PurchaseRepository purchaseRepository) {
        this.screeningRepository = screeningRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @Transactional
    public void execute(Long purchaseId) {
        Purchase purchase = purchaseRepository.findById(purchaseId).orElseThrow();
        Screening screening = purchase.getScreening();

        int availableSeats = screening.getAvailableSeats() - purchase.getTickets().size();

        screening.setAvailableSeats(availableSeats);
        screeningRepository.save(screening);
    }
}
