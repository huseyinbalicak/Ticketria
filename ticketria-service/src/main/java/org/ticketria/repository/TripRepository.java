package org.ticketria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.ticketria.model.Trip;
import org.ticketria.model.enums.VehicleType;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> , JpaSpecificationExecutor<Trip> {
   /* List<Trip> findByOriginCityOrDestinationCityOrDepartureDateOrVehicleType(
            String departureCity, String arrivalCity, LocalDate departureDate, VehicleType vehicleType);*/

    List<Trip> findByVehicleType(VehicleType vehicleType);

    List<Trip> findByOriginCityAndDestinationCity(String originCity, String destinationCity);


    Trip findByTripNumber(String tripNumber);


}
