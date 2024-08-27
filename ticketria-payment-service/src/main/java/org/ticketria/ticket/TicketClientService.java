package org.ticketria.ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketClientService {

    private final TicketClient ticketClient;

    public TicketPurchaseResponse getUserByEmail(String email){
        return ticketClient.getTicketsByEmail(email);
    }

    public List<PurchasedTicketInformationResponse> purchasedTicketInformationResponse(String email)
    {
        return ticketClient.getPurchasedTicketInformation(email);
    }


}