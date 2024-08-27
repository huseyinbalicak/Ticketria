package org.ticketria.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.ticketria.listener.dto.UserSendEmailRequest;
import org.ticketria.strategy.NotificationService;
import org.ticketria.strategy.SendNotificationRequest;
import org.ticketria.strategy.services.EmailService;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationListener {

    private final NotificationService notificationService;

    private final EmailService emailService;

    @RabbitListener(queues = "${rabbitmq.purchase.ticket.queue}")
    public void listenNotification(SendNotificationRequest sendNotificationRequest) {
        log.info("Notification received: {}", sendNotificationRequest);
        notificationService.sendNotification(sendNotificationRequest);
    }

    //kullanıcı kayıt işlemi sonrası sadece email ile bilgilendirildiği için:
    @RabbitListener(queues = "${rabbitmq.email.queue}")
    public void listenEmail(UserSendEmailRequest sendEmailRequest) {

        log.info("emailMessage: {}", sendEmailRequest);

        emailService.sendActivationEmail(sendEmailRequest.email(),sendEmailRequest.activationToken());

    }

}
