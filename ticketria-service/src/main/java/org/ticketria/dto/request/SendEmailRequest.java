package org.ticketria.dto.request;

import org.ticketria.model.enums.VehicleType;

import java.math.BigDecimal;

public record SendEmailRequest(
        String email, BigDecimal totalTicketPrice,
        String arrivalLocation, String departureLocation, VehicleType vehicleType
) {}