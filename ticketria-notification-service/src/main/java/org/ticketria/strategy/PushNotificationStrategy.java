package org.ticketria.strategy;

import org.springframework.stereotype.Service;
import org.ticketria.strategy.services.PushNotificationService;

@Service
public class PushNotificationStrategy implements NotificationStrategy {

    private final PushNotificationService pushNotificationService;

    public PushNotificationStrategy(PushNotificationService pushNotificationService) {
        this.pushNotificationService = pushNotificationService;
    }

    @Override
    public void send(SendNotificationRequest request) {
        pushNotificationService.sendPaymentDetailPush(
            request.recipient().toString(),
            request.totalTicketPrice(),
            request.arrivalLocation(),
            request.departureLocation(),
            request.vehicleType()
        );
    }
    @Override
    public NotificationType getNotificationType() {
        return NotificationType.PUSH;
    }

}
