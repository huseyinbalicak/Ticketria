package org.ticketria.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import org.ticketria.configuration.RabbitMQConfig;
import org.ticketria.dto.SendEmailRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMqProducer {

    private final AmqpTemplate rabbitTemplate;

    private final RabbitMQConfig rabbitMQConfig;

    public void sendEmail(SendEmailRequest sendEmailRequest) {
        rabbitTemplate.convertAndSend(rabbitMQConfig.getExchange(), rabbitMQConfig.getRoutingKey(), sendEmailRequest);

        log.info("message kuyruğa gönderildi. kuyruk:{}, message: {}", rabbitMQConfig.getQueueEmail(), sendEmailRequest);

    }

}