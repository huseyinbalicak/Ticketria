package org.ticketria.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ticketria.model.enums.VehicleType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TripRequest {

    private String originCity;

    private String destinationCity;

    private LocalDateTime departureDate;

    private LocalDateTime arrivalDate;

    private VehicleType vehicleType;

    private BigDecimal price;

}