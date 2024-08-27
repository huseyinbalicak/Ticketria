package org.ticketria.converter;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.ticketria.dto.request.TripRequest;
import org.ticketria.dto.response.TripResponse;
import org.ticketria.dto.response.TripSearchResponse;
import org.ticketria.model.Trip;

import java.util.List;
import java.util.UUID;

@Component
public class TripConverter {

    public Trip convertToEntity(TripRequest request) {
        Trip trip = new Trip();

        trip.setTripNumber(UUID.randomUUID().toString());
        trip.setOriginCity(request.getOriginCity());

        trip.setDestinationCity(request.getDestinationCity());
        trip.setDepartureDate(request.getDepartureDate());
        trip.setArrivalDate(request.getArrivalDate());
        trip.setVehicleType(request.getVehicleType());
        trip.setPrice(request.getPrice());
        return trip;
    }

    public static TripResponse toResponse(Trip trip) {
        return new TripResponse(
                trip.getTripNumber(),
                trip.getOriginCity(),
                trip.getDestinationCity(),
                trip.getDepartureDate(),
                trip.getArrivalDate(),
                trip.getVehicleType(),
                trip.getPrice(),
                trip.getCapacity(),
                trip.getAvailableCapacity()
        );
    }

    public static List<TripResponse> toResponse(List<Trip> trips)
    {
        return trips.stream().map(TripConverter::toResponse)
                .toList();
    }



    public static TripSearchResponse toResponse(Page<Trip> trips) {
        TripSearchResponse response = new TripSearchResponse();
        response.setTripResponses(toResponse(trips.getContent()));
        return response;
    }
}

