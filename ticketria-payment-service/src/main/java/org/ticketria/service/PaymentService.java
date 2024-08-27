package org.ticketria.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.ticketria.client.user.dto.response.FeignClientUserResponse;
import org.ticketria.client.user.service.UserClientService;
import org.ticketria.converter.PaymentConverter;
import org.ticketria.dto.request.PaymentRequest;
import org.ticketria.dto.response.PaymentResponse;
import org.ticketria.exception.NotFoundException;
import org.ticketria.model.Payment;
import org.ticketria.model.PaymentStatus;
import org.ticketria.repository.PaymentRepository;
import org.ticketria.ticket.PurchasedTicketInformationResponse;
import org.ticketria.ticket.TicketClientService;

import java.util.List;

@Slf4j
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserClientService userClientService;
    private final TicketClientService ticketClientService;

    public PaymentService(PaymentRepository paymentRepository, UserClientService userClientService, TicketClientService ticketClientService) {
        this.paymentRepository = paymentRepository;
        this.userClientService = userClientService;
        this.ticketClientService = ticketClientService;
    }

    public PaymentResponse createPayment(PaymentRequest request) {

        FeignClientUserResponse foundUser = userClientService.getUserByEmail(request.getEmail());

        List<PurchasedTicketInformationResponse> purchasedTicketInformationResponses=ticketClientService.
                purchasedTicketInformationResponse(foundUser.getEmail());


        if (foundUser == null) {
            throw new NotFoundException(foundUser.getUserId());
        }

        Payment payment = PaymentConverter.toEntity(request, PaymentStatus.PAID);

        paymentRepository.save(payment);

        return PaymentConverter.toResponse(payment);
    }

    public List<PaymentResponse> getAllPayments() {

        List<Payment> payments = paymentRepository.findAll();

        return PaymentConverter.toResponse(payments);
    }

}