package org.ticketria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ticketria.dto.request.TicketPurchaseRequest;
import org.ticketria.dto.response.PurchasedTicketInformationResponse;
import org.ticketria.dto.response.TicketPurchaseResponse;
import org.ticketria.model.Ticket;
import org.ticketria.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trips/user")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/purchase/{email}/{tripNumber}")
    public ResponseEntity<Void> purchaseTickets(@RequestBody List<TicketPurchaseRequest> request,
                                                @PathVariable String tripNumber,
                                                @PathVariable String email) {

        boolean isValid = ticketService.validateTicketPurchase(request, tripNumber, email);

        if (!isValid) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/tickets/{email}")
    public TicketPurchaseResponse getTicketsByEmail(@PathVariable String email) {
        return ticketService.getTicketsByEmail(email);
    }

    @GetMapping("/tickets/information/{email}")
    public ResponseEntity<List<PurchasedTicketInformationResponse>> getPurchasedTicketInformation(
            @PathVariable String email
            ) {

        List<PurchasedTicketInformationResponse> responses = ticketService.purchasedTicketInformationResponse(email);

        if (responses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(responses);
    }


    @GetMapping("/tickets")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }
}
