package org.ticketria.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TicketsResponse(
        int seatNumber,
        String ticketType,
        Long tripId,
        Long userId,
       /* Set<PassengerResponse> passengers,*/
        BigDecimal price,
        LocalDate purchaseDate
) {
}