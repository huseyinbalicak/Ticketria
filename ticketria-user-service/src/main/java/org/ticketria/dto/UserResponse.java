package org.ticketria.dto;


import lombok.Getter;
import lombok.Setter;
import org.ticketria.model.User;
import org.ticketria.model.enums.UserType;


@Getter
@Setter
public class UserResponse {

    private long id;
    private String username;
    private String email;
    private String phoneNumber;
    private UserType userType;


    public UserResponse(User user) {
        setId(user.getId());
        setUsername(user.getUsername());
        setEmail(user.getEmail());
        setPhoneNumber(user.getPhoneNumber());
        setUserType(user.getUserType());
    }

}