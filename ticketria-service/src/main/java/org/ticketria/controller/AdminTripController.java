package org.ticketria.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ticketria.converter.TripConverter;
import org.ticketria.dto.request.TripRequest;
import org.ticketria.dto.response.TripResponse;
import org.ticketria.model.Trip;
import org.ticketria.service.TripService;

@RestController
@RequestMapping("api/v1/trips/admin")
public class AdminTripController {


    private final TripService tripService;
    private final TripConverter tripConverter;

    public AdminTripController(TripService tripService, TripConverter tripConverter) {
        this.tripService = tripService;
        this.tripConverter = tripConverter;
    }


    @PostMapping
    public ResponseEntity<TripResponse> createTrip(@RequestBody @Valid TripRequest request) {
        Trip savedTrip=tripService.addTrip(tripConverter.convertToEntity(request));
        return new ResponseEntity<>(TripConverter.toResponse(savedTrip), HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{tripNumber}")
    public void cancelTrip(@PathVariable String tripNumber) {
        tripService.cancelTrip(tripNumber);
    }

}
