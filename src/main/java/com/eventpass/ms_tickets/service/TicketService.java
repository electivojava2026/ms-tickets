package com.eventpass.ms_tickets.service;

import com.eventpass.ms_tickets.dto.TicketResponse;
import com.eventpass.ms_tickets.model.Ticket;
import com.eventpass.ms_tickets.model.TicketStatus;
import com.eventpass.ms_tickets.repository.TicketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }

    public List<TicketResponse> findByOrderId(Long orderId) {
        return ticketRepository.findByOrderId(orderId).stream().map(this::toResponse).toList();
    }

    public TicketResponse generateTicket(Long eventId, Long orderId) {
        Ticket ticket = Ticket.builder()
                .ticketNumber(generateTicketNumber())
                .eventId(eventId)
                .orderId(orderId)
                .status(TicketStatus.GENERADO)
                .createdAt(LocalDateTime.now())
                .build();

        Ticket saved = ticketRepository.save(ticket);
        return toResponse(saved);
    }

    private String generateTicketNumber() {
        return "TKT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private TicketResponse toResponse(Ticket ticket) {
        return TicketResponse.builder()
                .id(ticket.getId())
                .ticketNumber(ticket.getTicketNumber())
                .eventId(ticket.getEventId())
                .orderId(ticket.getOrderId())
                .status(ticket.getStatus().name())
                .createdAt(ticket.getCreatedAt())
                .build();
    }
}
