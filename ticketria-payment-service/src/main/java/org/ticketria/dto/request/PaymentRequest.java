package org.ticketria.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ticketria.model.enums.PaymentMethod;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentRequest {

    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private String email;
}
