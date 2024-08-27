package org.ticketria.converter;

import org.springframework.stereotype.Component;
import org.ticketria.dto.request.RequestPassenger;
import org.ticketria.dto.response.PassengerResponse;
import org.ticketria.model.Passenger;
@Component
public class PassengerConverter {

    public static Passenger toEntity(RequestPassenger requestPassenger) {
        Passenger passenger = new Passenger();
        passenger.setFirstName(requestPassenger.getFirstName());
        passenger.setLastName(requestPassenger.getLastName());
        passenger.setIdentityNumber(requestPassenger.getIdentityNumber());
        passenger.setGender(requestPassenger.getGender());
        return passenger;
    }

    public static PassengerResponse toPassengerResponse(Passenger passenger) {
        return new PassengerResponse(
                passenger.getFirstName(),
                passenger.getLastName(),
                passenger.getGender()
        );
    }

}