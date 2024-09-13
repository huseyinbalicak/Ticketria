package org.ticketria.client.user.response;


import lombok.*;
import org.ticketria.client.user.response.enums.UserType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private long id;
    private String username;
    private String email;
    private String phoneNumber;
    private UserType userType;

}