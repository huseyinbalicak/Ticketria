package org.ticketria.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.ticketria.converter.TripConverter;
import org.ticketria.dto.request.TripSearchRequest;
import org.ticketria.dto.response.GenericResponse;
import org.ticketria.dto.response.TripResponse;
import org.ticketria.dto.response.TripSearchResponse;
import org.ticketria.model.Trip;
import org.ticketria.service.TripService;

@RestController
@RequestMapping("api/v1/trips/user")
public class TripController {

    private final TripService tripService;


    public TripController(TripService tripService) {
        this.tripService = tripService;

    }

    @GetMapping("/{tripNumber}")
    public TripResponse getTrip(@PathVariable String tripNumber)
    {
        Trip trip=tripService.findTrip(tripNumber);
        return TripConverter.toResponse(trip);
    }

    @GetMapping("/search")
    public GenericResponse<TripSearchResponse> getTrips(
            @RequestBody TripSearchRequest request,
            @PageableDefault(size = 10, sort = "departureDate", direction = Sort.Direction.ASC) Pageable pageable) {

        Page<Trip> trips = tripService.getAllTrips(request, pageable);
        return GenericResponse.success(TripConverter.toResponse(trips), HttpStatus.OK);
    }

}
