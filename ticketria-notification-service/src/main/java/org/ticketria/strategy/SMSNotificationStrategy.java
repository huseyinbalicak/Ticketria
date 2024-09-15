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
            request.getPhoneNumber(),
            request.getTotalTicketPrice(),
            request.getArrivalLocation(),
            request.getDepartureLocation(),
            request.getVehicleType()
        );
    }

}
