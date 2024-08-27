package org.ticketria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ticketria.model.User;
import org.ticketria.model.enums.UserType;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeignClientUserResponse {

    private Long userId;
    private String email;
    private UserType userType;

    public FeignClientUserResponse(User user) {
        setUserId(user.getId());

        setEmail(user.getEmail());
        setUserType(user.getUserType());
    }
}
