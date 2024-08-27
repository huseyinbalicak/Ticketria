package org.ticketria.dto;


import lombok.Getter;
import lombok.Setter;
import org.ticketria.model.User;


@Getter
@Setter
public class UserResponse {

    long id;
    String username;
    String email;

    public UserResponse(User user) {
        setId(user.getId());
        setUsername(user.getUsername());
        setEmail(user.getEmail());
    }

}