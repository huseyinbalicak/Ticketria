package org.ticketria.strategy;

import org.ticketria.listener.dto.VehicleType;

import java.math.BigDecimal;

public record SendNotificationRequest(
    RecipientType recipient,
    BigDecimal totalTicketPrice,
    String arrivalLocation,
    String departureLocation,
    VehicleType vehicleType,
    NotificationType notificationType
) {}
