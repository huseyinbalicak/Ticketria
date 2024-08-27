package org.ticketria.client.payment;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.ticketria.client.payment.service.dto.request.PaymentRequest;
import org.ticketria.client.payment.service.dto.response.PaymentResponse;

@FeignClient(value = "ticketria-payment-service", url = "localhost:8082/api/v1/payments")
public interface PaymentClient {

    @PostMapping
    PaymentResponse createPayment(@RequestBody PaymentRequest request);
}
