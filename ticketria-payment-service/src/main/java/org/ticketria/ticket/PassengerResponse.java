package org.ticketria.ticket;

import org.ticketria.ticket.enums.Gender;

public record PassengerResponse(
        String firstName,
        String lastName,
        Gender gender
) {}