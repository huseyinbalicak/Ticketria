package org.ticketria.dto;


import lombok.Getter;
import lombok.Setter;
import org.ticketria.client.user.response.UserResponse;

@Getter
@Setter
public class AuthResponse {

    private UserResponse userResponse;
    private String token;

}
