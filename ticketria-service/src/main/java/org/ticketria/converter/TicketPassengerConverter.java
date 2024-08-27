package org.ticketria.converter;

import org.ticketria.dto.request.RequestPassenger;
import org.ticketria.dto.request.TicketRequest;
import org.ticketria.dto.response.PassengerResponse;
import org.ticketria.dto.response.TicketResponse;
import org.ticketria.model.Passenger;
import org.ticketria.model.Ticket;
import org.ticketria.model.Trip;

import java.time.LocalDate;

public class TicketPassengerConverter {

    public static Ticket convertToTicket(TicketRequest ticketRequest, Trip foundTrip, Long userId) {
        Ticket ticket = new Ticket();
        ticket.setTrip(foundTrip);
        ticket.setUserId(userId);
        ticket.setSeatNumber(ticketRequest.getSeatNumber());
        ticket.setPrice(foundTrip.getPrice());
        ticket.setPurchaseDate(LocalDate.now());
        return ticket;
    }

    public static Passenger convertToPassenger(RequestPassenger requestPassenger, Ticket ticket) {
        Passenger passengerEntity = new Passenger();
        passengerEntity.setTicket(ticket);
        passengerEntity.setFirstName(requestPassenger.getFirstName());
        passengerEntity.setLastName(requestPassenger.getLastName());
        passengerEntity.setIdentityNumber(requestPassenger.getIdentityNumber());
        passengerEntity.setTripNumber(ticket.getTrip().getTripNumber());
        passengerEntity.setUserId(ticket.getUserId());
        passengerEntity.setGender(requestPassenger.getGender());
        return passengerEntity;
    }

    public static TicketResponse toTicketResponse(Ticket ticket) {
        PassengerResponse passengerResponses = PassengerConverter.toPassengerResponse(ticket.getPassenger());

        return new TicketResponse(
                ticket.getSeatNumber(),
                passengerResponses,
                ticket.getPrice()
        );
    }


}
