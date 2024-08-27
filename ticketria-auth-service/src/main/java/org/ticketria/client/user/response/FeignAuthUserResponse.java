package org.ticketria.client.user.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ticketria.model.Role;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeignAuthUserResponse {

    private Long userId;
    private String email;
    private String username;
    private String password;
    private boolean isActive;
    private Set<Role> roles;
}