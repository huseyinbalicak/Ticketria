package org.ticketria.ticket;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchasedTicketInformationResponse{
        private String name;
        private String surname;
        /*private LocalDate departureDate;
        private LocalDate arrivalDate;*/
        private int seatNumber;
/*
        private LocalDate purchaseDate;
*/
}
