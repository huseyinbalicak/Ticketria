package org.ticketria.client.user.dto.response;
import lombok.*;
import org.ticketria.client.user.dto.response.enums.UserType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private Long userId;
    private String email;
    private UserType userType;
    private String bio;
}