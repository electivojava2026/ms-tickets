package com.eventpass.ms_tickets.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String ticketNumber;

    @Column(nullable = false)
    private Long eventId;

    @Column(nullable = false)
    private Long orderId;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

}
