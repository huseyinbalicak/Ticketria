package org.ticketria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.ticketria.model.Passenger;
import org.ticketria.model.enums.Gender;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    @Query("SELECT COUNT(p) FROM Passenger p JOIN p.ticket t WHERE t.trip.tripNumber = :tripNumber")
    long countPassengersByTripNumber(@Param("tripNumber") String tripNumber);

    @Query("SELECT COUNT(p) FROM Passenger p JOIN p.ticket t WHERE t.trip.tripNumber = :tripNumber AND p.gender = :gender")
    long countPassengersByTripNumberAndGender(@Param("tripNumber") String tripNumber, @Param("gender") Gender gender);

    long countPassengersByTripNumberAndUserId(String tripNumber, Long userId);

    long countPassengersByTripNumberAndGenderAndUserId(String tripNumber, Gender gender, Long userId);
}
