package org.ticketria.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.ticketria.listener.dto.UserSendEmailRequest;
import org.ticketria.strategy.*;
import org.ticketria.strategy.services.EmailService;
import org.ticketria.strategy.services.SmsService;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationListener {

    private final NotificationService notificationService;

    private final EmailService emailService;
    private final SmsService smsService;

    @RabbitListener(queues = "${rabbitmq.purchase.ticket.queue}")
    public void listenNotification(SendNotificationRequest sendNotificationRequest) {

        log.info("Notification received: {}", sendNotificationRequest);

        List<NotificationType> notificationTypes = sendNotificationRequest.getNotificationTypes();

        for(NotificationType type : notificationTypes) {
            notificationService.setNotificationStrategy(getStrategy(type));
            notificationService.send(sendNotificationRequest);
        }
    }

    //kullanıcı kayıt işlemi sonrası sadece email ile bilgilendirildiği için:
    @RabbitListener(queues = "${rabbitmq.email.queue}")
    public void listenEmail(UserSendEmailRequest sendEmailRequest) {

        log.info("emailMessage: {}", sendEmailRequest);

        emailService.sendActivationEmail(sendEmailRequest.email(),sendEmailRequest.activationToken());

    }


    private NotificationStrategy getStrategy(NotificationType type) {
        if (type == NotificationType.EMAIL) {
            return new EmailNotificationStrategy(emailService);
        } else if (type == NotificationType.SMS) {
            return new SMSNotificationStrategy(smsService);
        } else {
            throw new UnsupportedOperationException("Unsupported notification type: " + type);
        }
    }

}
