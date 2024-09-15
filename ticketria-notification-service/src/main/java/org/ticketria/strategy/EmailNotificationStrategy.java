package org.ticketria.strategy;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.ticketria.strategy.services.EmailService;

@Service
@Primary
public class EmailNotificationStrategy implements NotificationStrategy {

    private final EmailService emailService;

    public EmailNotificationStrategy(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void send(SendNotificationRequest request) {
        emailService.sendPaymentDetailEmail(
            request.getEmail(),
            request.getTotalTicketPrice(),
            request.getArrivalLocation(),
            request.getDepartureLocation(),
            request.getVehicleType()
        );
    }

}
