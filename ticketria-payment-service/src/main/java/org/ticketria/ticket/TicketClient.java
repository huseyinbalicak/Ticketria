package org.ticketria.ticket;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//http://localhost:8088/api/v1/trips/user/tickets/{{email}}
//http://localhost:8088/api/v1/trips/user/tickets/information/{{email}}/{{tripNumber}}
@FeignClient(value = "ticketria-service", url = "localhost:8088/api/v1/trips/user/tickets")
public interface TicketClient {

    @GetMapping("/tickets/{email}")
    TicketPurchaseResponse getTicketsByEmail(@PathVariable String email);

    @GetMapping("/information/{email}")
    List<PurchasedTicketInformationResponse> getPurchasedTicketInformation(@PathVariable String email);
}