package org.ticketria.strategy;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Setter
@Slf4j
@Service
public class NotificationService {

    private NotificationStrategy notificationStrategy;

    public NotificationService(NotificationStrategy notificationStrategy) {
        this.notificationStrategy = notificationStrategy;
    }


    public void send(SendNotificationRequest request) {
        notificationStrategy.send(request);
    }
}
