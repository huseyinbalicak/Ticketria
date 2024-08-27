package org.ticketria.dto.request;

import org.ticketria.model.enums.NotificationType;
import org.ticketria.model.enums.RecipientType;
import org.ticketria.model.enums.VehicleType;

import java.math.BigDecimal;

public record SendNotificationRequest(
    RecipientType recipientType,
    BigDecimal totalTicketPrice,
    String arrivalLocation,
    String departureLocation,
    VehicleType vehicleType,
    NotificationType notificationType
) {}
