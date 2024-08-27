package org.ticketria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.ticketria.model.Ticket;
import org.ticketria.model.Trip;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByTrip(Trip trip);

    long countByTripId(Long tripId);

    @Query("SELECT COUNT(t) FROM Ticket t WHERE t.trip.tripNumber = :tripNumber")
    long countByTripNumber(@Param("tripNumber") String tripNumber);


    List<Ticket> findByUserId(Long userId);


    long countByTripIdAndSeatNumber(Long tripId, int seatNumber);

}
