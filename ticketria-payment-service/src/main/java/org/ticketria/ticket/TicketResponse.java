package org.ticketria.ticket;

import java.math.BigDecimal;
import java.util.Set;

public record TicketResponse(
        int seatNumber,
        Set<PassengerResponse> passengers,
        BigDecimal price
) {}