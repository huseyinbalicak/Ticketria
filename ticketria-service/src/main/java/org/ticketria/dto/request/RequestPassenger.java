package org.ticketria.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.ticketria.model.enums.Gender;

@Getter
@Setter
public class RequestPassenger {

    private String firstName;
    private String lastName;
    private String identityNumber;
    private Gender gender;

    public RequestPassenger(String firstName, String lastName, String identityNumber, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.identityNumber = identityNumber;
        this.gender = gender;
    }
}
