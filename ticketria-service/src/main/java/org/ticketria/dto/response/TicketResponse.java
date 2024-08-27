package org.ticketria.dto.response;

import java.math.BigDecimal;

public record TicketResponse(
        int seatNumber,
        PassengerResponse passenger,
        BigDecimal price
) {}
