package org.ticketria.strategy;

import org.springframework.stereotype.Service;
import org.ticketria.strategy.services.EmailService;

@Service
public class EmailNotificationStrategy implements NotificationStrategy {

    private final EmailService emailService;

    public EmailNotificationStrategy(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void send(SendNotificationRequest request) {
        emailService.sendPaymentDetailEmail(
            request.recipient().toString(),
            request.totalTicketPrice(),
            request.arrivalLocation(),
            request.departureLocation(),
            request.vehicleType()
        );
    }

    @Override
    public NotificationType getNotificationType() {
        return NotificationType.EMAIL;
    }
}
