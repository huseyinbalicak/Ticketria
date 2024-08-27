package org.ticketria.strategy.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.ticketria.listener.dto.VehicleType;

import java.math.BigDecimal;
@Slf4j
@Service
public class PushNotificationService {
    public void sendPaymentDetailPush(String recipient, BigDecimal totalTicketPrice, String arrivalLocation,
                                      String departureLocation, VehicleType vehicleType) {
        String message = createPushMessage(totalTicketPrice, arrivalLocation, departureLocation, vehicleType);

        log.info("Sending push notification to {}: {}", recipient, message);

    }

    private String createPushMessage(BigDecimal totalTicketPrice, String arrivalLocation,
                                     String departureLocation, VehicleType vehicleType) {
        return String.format(
                "Your ticket purchase is confirmed! %n" +
                        "Total Price: %s %n" +
                        "Departure: %s %n" +
                        "Arrival: %s %n" +
                        "Vehicle: %s",
                totalTicketPrice,
                departureLocation,
                arrivalLocation,
                vehicleType
        );
    }
}
