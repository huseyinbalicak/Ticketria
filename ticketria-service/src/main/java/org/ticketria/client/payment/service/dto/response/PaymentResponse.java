package org.ticketria.client.payment.service.dto.response;

import lombok.*;
import org.ticketria.client.payment.service.dto.response.enums.PaymentMethod;
import org.ticketria.client.payment.service.dto.response.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaymentResponse {

    private BigDecimal amount;
    private LocalDateTime createdDateTime;
    private PaymentStatus paymentStatus;
    private PaymentMethod paymentMethod;
    private Long userId;

}
