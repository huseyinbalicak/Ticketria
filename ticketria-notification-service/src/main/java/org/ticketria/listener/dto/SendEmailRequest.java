package org.ticketria.listener.dto;

import java.math.BigDecimal;

public record SendEmailRequest(
        String email, BigDecimal totalTicketPrice, /*LocalDate arrivalDate, LocalDate departureDate,*/ String arrivalLocation, String departureLocation, VehicleType vehicleType
){

}