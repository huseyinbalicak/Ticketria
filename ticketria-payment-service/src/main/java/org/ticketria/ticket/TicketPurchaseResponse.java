package org.ticketria.ticket;

import java.util.List;

public record TicketPurchaseResponse(
        List<TicketResponse> tickets,
        String email,
        int numberOfTickets
) {}
