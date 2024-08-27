package org.ticketria.client.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ticketria.client.user.dto.response.enums.UserType;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeignClientUserResponse {

    private Long userId;
    private String email;
    private UserType userType;

}