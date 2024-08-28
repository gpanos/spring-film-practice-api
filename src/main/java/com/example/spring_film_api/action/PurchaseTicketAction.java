package com.example.spring_film_api.action;

import java.math.BigDecimal;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import com.example.spring_film_api.dto.PurchaseDTO;
import com.example.spring_film_api.dto.PurchaseRequest;
import com.example.spring_film_api.dto.TicketRequestDTO;
import com.example.spring_film_api.event.PurchaseCreatedEvent;
import com.example.spring_film_api.exception.NoSeatsLeftException;
import com.example.spring_film_api.exception.PastScreeningException;
import com.example.spring_film_api.mapper.PurchaseMapper;
import com.example.spring_film_api.model.Purchase;
import com.example.spring_film_api.model.Screening;
import com.example.spring_film_api.model.Ticket;
import com.example.spring_film_api.model.User;
import com.example.spring_film_api.repository.PurchaseRepository;
import com.example.spring_film_api.repository.ScreeningRepository;
import com.example.spring_film_api.repository.UserRepository;
import jakarta.transaction.Transactional;

@Component
public class PurchaseTicketAction {
    private final ScreeningRepository screeningRepository;
    private final PurchaseMapper purchaseMapper;
    private final UserRepository userRepository;
    private final PurchaseRepository purchaseRepository;
    private ApplicationEventPublisher eventPublisher;

    public PurchaseTicketAction(ScreeningRepository screeningRepository,
            PurchaseMapper purchaseMapper, UserRepository userRepository,
            PurchaseRepository purchaseRepository, ApplicationEventPublisher eventPublisher) {
        this.screeningRepository = screeningRepository;
        this.purchaseMapper = purchaseMapper;
        this.userRepository = userRepository;
        this.purchaseRepository = purchaseRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public PurchaseDTO execute(PurchaseRequest request, Long screeningId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Screening screening = screeningRepository.findById(screeningId)
                .orElseThrow(() -> new RuntimeException("Screening not found"));

        ensureSeatsAvailable(screening, request.getTickets().size());
        ensureScreeningIsInTheFuture(screening);

        Purchase purchase = new Purchase();
        purchase.setPaid(true);
        purchase.setScreening(screening);
        purchase.setTotalAmount(screening.getTicketPrice()
                .multiply(BigDecimal.valueOf(request.getTickets().size())));
        purchase.setUser(user);

        for (TicketRequestDTO ticketRequest : request.getTickets()) {
            Ticket ticket = new Ticket();
            ticket.setPurchase(purchase);
            ticket.setFirstName(ticketRequest.getFirstName());
            ticket.setLastName(ticketRequest.getLastName());
            ticket.setSeatNumber(ticketRequest.getSeatNumber());
            purchase.getTickets().add(ticket);
        }

        Purchase savedPurchase = purchaseRepository.save(purchase);

        eventPublisher.publishEvent(new PurchaseCreatedEvent(savedPurchase.getId()));

        return purchaseMapper.toPurchaseDTO(savedPurchase);
    }

    private void ensureSeatsAvailable(Screening screening, int ticketsCount)
            throws NoSeatsLeftException {
        if (screening.getAvailableSeats() < ticketsCount) {
            throw new NoSeatsLeftException("No seats left");
        }
    }

    private void ensureScreeningIsInTheFuture(Screening screening) throws PastScreeningException {
        if (screening.isInPast()) {
            throw new PastScreeningException("Screening is in the past");
        }
    }
}
