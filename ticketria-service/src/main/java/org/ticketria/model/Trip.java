package org.ticketria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.ticketria.model.enums.VehicleType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Trip {
    @Id
    @GeneratedValue
    private long id;

    private String tripNumber;

    private String originCity;

    private String destinationCity;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime departureDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime arrivalDate;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    private BigDecimal price;

    @OneToMany(mappedBy = "trip")
    private Set<Ticket> tickets=new HashSet<>();

    public int getCapacity() {
        return vehicleType.getCapacity();
    }

    public int getAvailableCapacity() {
        return getCapacity() - tickets.size();
    }

    public boolean hasCapacity(int numberOfTickets) {
        return getAvailableCapacity() >= numberOfTickets;
    }

}
