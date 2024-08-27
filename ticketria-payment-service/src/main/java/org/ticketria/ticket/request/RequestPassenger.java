package org.ticketria.ticket.request;

import lombok.Getter;
import lombok.Setter;
import org.ticketria.ticket.enums.Gender;

@Getter
@Setter
public class RequestPassenger {

    private String firstName;
    private String lastName;
    private String identityNumber;
    private Gender gender;

}
