package org.ticketria.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.ticketria.listener.dto.SendEmailRequest;
import org.ticketria.listener.dto.UserSendEmailRequest;
import org.ticketria.service.EmailService;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmailListener {

    private final EmailService emailService;
    ObjectMapper mapper = new ObjectMapper();

    @RabbitListener(queues = "${rabbitmq.email.queue}")
    public void listenEmail(UserSendEmailRequest sendEmailRequest) {

        log.info("emailMessage: {}", sendEmailRequest);

        emailService.sendActivationEmail(sendEmailRequest.email(),sendEmailRequest.activationToken());

    }

    @RabbitListener(queues = "${rabbitmq.purchase.ticket.queue}")
    public void listenEmailPayment(SendEmailRequest sendEmailRequest) throws JsonProcessingException {

        log.info("emailMessage: {}", sendEmailRequest);

        emailService.sendPaymentDetailEmail(sendEmailRequest.email(),sendEmailRequest.totalTicketPrice()
        /*mapper.writeValueAsString(sendEmailRequest.arrivalDate())*//*,mapper.writeValueAsString(sendEmailRequest.departureDate())*/,sendEmailRequest.arrivalLocation()
        ,sendEmailRequest.departureLocation(),sendEmailRequest.vehicleType());
        System.out.println(sendEmailRequest);
    }






}