package org.ticketria.repository.spesification;

import jakarta.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.ticketria.dto.request.TripSearchRequest;
import org.ticketria.model.Trip;
import org.ticketria.model.constant.TripEntityConstants;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TripSpecification {

    public static Specification<Trip> initSpecification(TripSearchRequest request) {

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicateList = new ArrayList<>();

            if (request.getOriginCity() != null) {
                predicateList.add(criteriaBuilder.equal(root.get(TripEntityConstants.ORIGIN_CITY), request.getOriginCity()));
            }

            if (request.getDestinationCity() != null) {
                predicateList.add(criteriaBuilder.equal(root.get(TripEntityConstants.DESTINATION_CITY), request.getDestinationCity()));
            }

            if (request.getOriginCity() != null && request.getDestinationCity() != null) {
                Predicate originToDestination = criteriaBuilder.and(
                        criteriaBuilder.equal(root.get(TripEntityConstants.ORIGIN_CITY), request.getOriginCity()),
                        criteriaBuilder.equal(root.get(TripEntityConstants.DESTINATION_CITY), request.getDestinationCity())
                );
                predicateList.add(originToDestination);
            }

            if (request.getDate() != null) {
                Predicate departureDatePredicate = criteriaBuilder.equal(root.get(TripEntityConstants.DEPARTURE_DATE), request.getDate());
                Predicate arrivalDatePredicate = criteriaBuilder.equal(root.get(TripEntityConstants.ARRIVAL_DATE), request.getDate());
                predicateList.add(criteriaBuilder.or(departureDatePredicate, arrivalDatePredicate));
            }

            if (request.getVehicleType() != null) {
                predicateList.add(criteriaBuilder.equal(root.get(TripEntityConstants.VEHICLE_TYPE), request.getVehicleType()));
            }

            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
