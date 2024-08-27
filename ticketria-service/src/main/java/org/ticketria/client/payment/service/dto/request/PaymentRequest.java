package org.ticketria.client.payment.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ticketria.client.payment.service.dto.response.enums.PaymentMethod;
import org.ticketria.dto.request.TicketPurchaseRequest;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentRequest {

    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private String email;

    private List<TicketPurchaseRequest> ticketPurchaseRequest;


}
