package org.ticketria.dto.request;

import lombok.*;
import org.ticketria.model.enums.VehicleType;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TripSearchRequest {
    private String originCity;
    private String destinationCity;
    private VehicleType vehicleType;
    private LocalDate date;

}
