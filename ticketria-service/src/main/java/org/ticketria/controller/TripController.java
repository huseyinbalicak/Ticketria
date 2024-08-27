package org.ticketria.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ticketria.converter.TripConverter;
import org.ticketria.dto.request.TripRequest;
import org.ticketria.dto.request.TripSearchRequest;
import org.ticketria.dto.response.GenericResponse;
import org.ticketria.dto.response.TripResponse;
import org.ticketria.dto.response.TripSearchResponse;
import org.ticketria.model.Trip;
import org.ticketria.service.TripService;

@RestController
@RequestMapping("api/v1/trips")
public class TripController {


    private final TripService tripService;

    private final TripConverter tripConverter;


    public TripController(TripService tripService, TripConverter tripConverter) {
        this.tripService = tripService;
        this.tripConverter = tripConverter;
    }



    @PostMapping
    public ResponseEntity<TripResponse> createTrip(@RequestBody @Valid TripRequest request) {
        Trip savedTrip=tripService.addTrip(tripConverter.convertToEntity(request));
        return new ResponseEntity<>(TripConverter.toResponse(savedTrip), HttpStatus.CREATED);
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


    @DeleteMapping("/{tripNumber}")
    public void cancelTrip(@PathVariable String tripNumber) {
        tripService.cancelTrip(tripNumber);
    }


}
