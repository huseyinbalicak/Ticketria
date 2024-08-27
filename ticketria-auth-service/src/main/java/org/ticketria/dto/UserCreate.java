package org.ticketria.dto;

import org.ticketria.client.user.response.enums.UserType;

public record UserCreate(


        String username,
        String email,
        String password,
        UserType userType

) {
}


