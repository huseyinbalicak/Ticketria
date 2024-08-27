package org.ticketria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ticket {

    @Id
    @GeneratedValue
    private long id;

    private int seatNumber;

    @ManyToOne
    private Trip trip;

    private Long userId;

    @OneToOne
    private Passenger passenger;

    private BigDecimal price;
    private LocalDate purchaseDate;

}
