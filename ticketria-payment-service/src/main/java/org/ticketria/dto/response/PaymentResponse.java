package org.ticketria.dto.response;

import lombok.*;
import org.ticketria.model.PaymentStatus;
import org.ticketria.model.enums.PaymentMethod;

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
    private String email;

}
