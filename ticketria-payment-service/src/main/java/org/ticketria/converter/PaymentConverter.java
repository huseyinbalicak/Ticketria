package org.ticketria.converter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.ticketria.dto.request.PaymentRequest;
import org.ticketria.dto.response.PaymentResponse;
import org.ticketria.model.Payment;
import org.ticketria.model.PaymentStatus;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentConverter {


    public static Payment toEntity(PaymentRequest paymentRequest, PaymentStatus paymentStatus){
        return new Payment(paymentRequest.getAmount(),LocalDateTime.now(),paymentStatus,paymentRequest.getEmail());
    }

    public static PaymentResponse toResponse(Payment payment) {
        return PaymentResponse.builder()
                .amount(payment.getAmount())
                .createdDateTime(payment.getCreatedDateTime())
                .email(payment.getEmail())
                .paymentStatus(payment.getPaymentStatus())
                .build();
    }

    public static List<PaymentResponse> toResponse(List<Payment> payments) {
        return payments.stream()
                .map(PaymentConverter::toResponse)
                .toList();
    }
}
