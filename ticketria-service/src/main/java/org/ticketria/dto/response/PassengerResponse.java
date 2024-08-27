package org.ticketria.dto.response;

import org.ticketria.model.enums.Gender;

public record PassengerResponse(
        String firstName,
        String lastName,
        Gender gender
) {}
