package org.ticketria.strategy;

import org.springframework.stereotype.Service;
import org.ticketria.strategy.services.SmsService;

@Service
public class SMSNotificationStrategy implements NotificationStrategy {

    private final SmsService smsService;

    public SMSNotificationStrategy(SmsService smsService) {
        this.smsService = smsService;
    }

    @Override
    public void send(SendNotificationRequest request) {
        smsService.sendPaymentDetailSms(
            request.phoneNumber(),
            request.totalTicketPrice(),
            request.arrivalLocation(),
            request.departureLocation(),
            request.vehicleType()
        );
    }

    @Override
    public NotificationType getNotificationType() {
        return NotificationType.SMS;
    }
}
