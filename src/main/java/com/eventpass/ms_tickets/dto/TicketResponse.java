package com.eventpass.ms_tickets.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TicketResponse(
        Long id,
        String ticketNumber,
        Long eventId,
        Long orderId,
        String status,
        LocalDateTime createdAt
) {}
