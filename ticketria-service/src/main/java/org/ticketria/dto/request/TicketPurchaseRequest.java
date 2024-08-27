package org.ticketria.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TicketPurchaseRequest {

        private List<TicketRequest> tickets;
}
