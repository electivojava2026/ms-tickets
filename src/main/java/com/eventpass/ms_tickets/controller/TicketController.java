package com.eventpass.ms_tickets.controller;

import com.eventpass.ms_tickets.dto.TicketResponse;
import com.eventpass.ms_tickets.model.Ticket;
import com.eventpass.ms_tickets.service.TicketService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<Ticket>> getTickets() {
        List<Ticket> tickets = ticketService.findAllTickets();

        if (!tickets.isEmpty()) {
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    // Solo STAFF y la cuenta de servicio de ms-ordenes (que valida que la orden sea del comprador)
    @GetMapping("/order/{orderId}")
    public List<TicketResponse> getTicketsByOrder(@PathVariable Long orderId) {
        return ticketService.findByOrderId(orderId);
    }

    @PostMapping("/generate")
    @ResponseStatus(HttpStatus.CREATED)
    public TicketResponse generateTicket(
            @RequestParam Long eventId,
            @RequestParam Long orderId) {
        return ticketService.generateTicket(eventId, orderId);
    }
}
