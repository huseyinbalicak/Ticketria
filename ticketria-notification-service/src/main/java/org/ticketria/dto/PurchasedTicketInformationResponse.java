package org.ticketria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchasedTicketInformationResponse{
        private String name;
        private String surname;
        /*private LocalDate departureDate;
        private LocalDate arrivalDate;*/
        private int seatNumber;
        /*private LocalDate purchaseDate;*/
}