package org.ticketria.ticket.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TicketPurchaseRequest {

        private List<TicketRequest> tickets;
        private int numberOfTickets;

}