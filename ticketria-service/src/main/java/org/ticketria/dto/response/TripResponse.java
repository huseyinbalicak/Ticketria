package org.ticketria.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.ticketria.model.enums.VehicleType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TripResponse {


    private String tripNumber;
    private String originCity;
    private String destinationCity;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private VehicleType vehicleType;
    private BigDecimal price;
    private int capacity;
    private int availableCapacity;
}