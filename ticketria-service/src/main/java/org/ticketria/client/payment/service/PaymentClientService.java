package org.ticketria.client.payment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ticketria.client.payment.PaymentClient;
import org.ticketria.client.payment.service.dto.request.PaymentRequest;

@Service
@RequiredArgsConstructor
public class PaymentClientService {

    private final PaymentClient paymentClient;

    public void createPayment(PaymentRequest request) {
        paymentClient.createPayment(request);
    }
}
