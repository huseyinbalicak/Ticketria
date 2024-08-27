package org.ticketria.dto;


import lombok.Getter;
import lombok.Setter;
import org.ticketria.client.user.response.UserResponse;
import org.ticketria.token.Token;

@Getter
@Setter
public class AuthResponse {

    private UserResponse userResponse;
    private Token token;

}
