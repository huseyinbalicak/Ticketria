package org.ticketria.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import org.ticketria.configuration.RabbitMQConfig;
import org.ticketria.dto.request.SendEmailRequest;
import org.ticketria.dto.request.SendNotificationRequest;


@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMqProducer  {

    private final AmqpTemplate rabbitTemplate;

    private final RabbitMQConfig rabbitMQConfig;

    public void sendNotification(SendEmailRequest sendEmailRequest) {
        rabbitTemplate.convertAndSend(rabbitMQConfig.getExchange(), rabbitMQConfig.getRoutingkey(), sendEmailRequest);

        log.info("message kuyruğa gönderildi. kuyruk:{}, message: {}", rabbitMQConfig.getQueueName(), sendEmailRequest);

    }

    public void sendNotification(SendNotificationRequest sendNotificationRequest) {
        rabbitTemplate.convertAndSend(rabbitMQConfig.getExchange(), rabbitMQConfig.getRoutingkey(), sendNotificationRequest);
        log.info("Mesaj kuyruğa gönderildi: {}, message: {}", rabbitMQConfig.getQueueName(), sendNotificationRequest);
    }




}