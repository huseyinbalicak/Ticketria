package org.ticketria.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.ticketria.dto.request.TripSearchRequest;
import org.ticketria.model.Trip;
import org.ticketria.repository.TripRepository;
import org.ticketria.repository.spesification.TripSpecification;
import org.ticketria.util.CacheNames;

@Slf4j
@RequiredArgsConstructor
@Service
public class TripService {
    private final TripRepository tripRepository;

    @Caching(
            evict = {
                    @CacheEvict(value = CacheNames.TRIPS, allEntries = true, condition = "#trip.tripNumber != null")
            }
    )
    public Trip addTrip(Trip trip) {
        return tripRepository.save(trip);
    }


    @Cacheable(CacheNames.TRIPS)
    public Page<Trip> getAllTrips(TripSearchRequest request, Pageable pageable) {
        Specification<Trip> specification = TripSpecification.initSpecification(request);
        return tripRepository.findAll(specification, pageable);
    }

    public void cancelTrip(String tripNumber) {
        Trip foundTrip=findTrip(tripNumber);
        tripRepository.delete(foundTrip);
    }

    public Trip findTrip(String tripNumber) {
        return tripRepository.findByTripNumber(tripNumber);
    }
}
