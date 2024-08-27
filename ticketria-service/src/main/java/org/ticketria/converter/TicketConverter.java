package org.ticketria.converter;

import org.springframework.stereotype.Component;

@Component
public class TicketConverter {

    /*public static Passenger toTicketEntity(TicketPurchaseRequest request) {
        if (request == null || request.requestPassenger() == null) {
            throw new IllegalArgumentException("Request or requestPassenger cannot be null");
        }

        RequestPassenger requestPassenger = request.requestPassenger();

        Passenger passenger = new Passenger();
        passenger.setFirstName(requestPassenger.getFirstName());
        passenger.setLastName(requestPassenger.getLastName());
        passenger.setPhoneNumber(requestPassenger.getPhoneNumber());
        passenger.setIdentityNumber(requestPassenger.getIdentityNumber());
        passenger.setGender(requestPassenger.getGender());
        return passenger;
    }*/


  /*  public static List<Ticket> toTicketEntities(TicketPurchaseRequest request, Trip foundTrip) {
        return request.tickets().stream()
                .map(ticketRequest -> {
                    Ticket ticket = new Ticket();
                    ticket.setSeatNumber(ticketRequest.getSeatNumber());
                    ticket.setTicketType(ticketRequest.getTicketType());
                    ticket.setTrip(foundTrip);
                    ticket.setPassenger(PassengerConverter.toEntity(ticketRequest.getPassenger()));
                    ticket.setPrice(ticketRequest.getPrice());
                    ticket.setPurchaseDate(LocalDate.now());
                    return ticket;
                })
                .collect(Collectors.toList());
    }
*/



}
