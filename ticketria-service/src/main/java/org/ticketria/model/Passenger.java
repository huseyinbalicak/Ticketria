package org.ticketria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ticketria.model.enums.Gender;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Passenger {
    @Id
    @GeneratedValue
    private long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String identityNumber;

    private String tripNumber;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne
    private Ticket ticket;

}
