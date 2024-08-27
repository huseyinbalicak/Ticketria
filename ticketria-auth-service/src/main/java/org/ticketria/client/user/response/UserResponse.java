package org.ticketria.client.user.response;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserResponse {

    long id;
    String email;

    public UserResponse(FeignAuthUserResponse user) {
        setId(user.getUserId());
        setEmail(user.getEmail());

    }

}
