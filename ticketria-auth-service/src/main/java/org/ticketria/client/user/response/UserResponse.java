package org.ticketria.client.user.response;

import lombok.Getter;
import lombok.Setter;
import org.ticketria.dto.UserAuthInfo;

@Getter
@Setter
public class UserResponse {

    long id;
    String email;

    public UserResponse(UserAuthInfo user) {
        setId(Long.parseLong(user.getId()));
        setEmail(user.getEmail());

    }

}
