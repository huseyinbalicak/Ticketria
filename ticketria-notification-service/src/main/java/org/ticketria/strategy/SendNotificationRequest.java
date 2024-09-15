package org.ticketria.strategy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ticketria.listener.dto.VehicleType;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendNotificationRequest{

    private String email;
    private String phoneNumber;
    private BigDecimal totalTicketPrice;
    private String arrivalLocation;
    private String departureLocation;
    private VehicleType vehicleType;
    private List<NotificationType> notificationTypes;
}
