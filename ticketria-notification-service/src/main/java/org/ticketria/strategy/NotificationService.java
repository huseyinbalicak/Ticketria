package org.ticketria.strategy;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final Map<NotificationType, NotificationStrategy> strategies;

    public NotificationService(List<NotificationStrategy> strategyList) {
        strategies = strategyList.stream().collect(Collectors.toMap(
                NotificationStrategy::getNotificationType,
                strategy -> strategy
        ));
    }

    public void sendNotification(SendNotificationRequest request) {
        NotificationStrategy strategy = strategies.get(request.notificationType());
        if (strategy != null) {
            strategy.send(request);
        } else {
            throw new UnsupportedOperationException("Bildirim tipi desteklenmiyor: " + request.notificationType());
        }
    }
}
