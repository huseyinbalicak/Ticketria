package org.ticketria.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.ticketria.client.user.response.UserResponse;
import org.ticketria.model.Ticket;

import java.util.List;

@Getter
@Setter
public class PurchasedTicketInformationResponse{
        private String name;
        private String surname;
        /*private String fromWhere;
        private String toWhere;*/
        /*private LocalDate departureDate;
        private LocalDate arrivalDate;*/
        private int seatNumber;



    public PurchasedTicketInformationResponse(List<Ticket> ticket, UserResponse userResponse)
    {
        setName(userResponse.getEmail());
        setSurname(userResponse.getEmail());
       /* setDepartureDate(trip.getDepartureDate());
        setArrivalDate(trip.getArrivalDate());*/
        setSeatNumber(ticket.get(0).getSeatNumber());
    }
}

