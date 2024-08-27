package org.ticketria.client.user.response;


import lombok.*;
import org.ticketria.client.user.response.enums.UserType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private Long userId;
    private String email;
    private UserType userType;
}