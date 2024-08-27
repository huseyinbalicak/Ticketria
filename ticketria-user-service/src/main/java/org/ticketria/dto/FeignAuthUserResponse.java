package org.ticketria.dto;

import lombok.*;
import org.ticketria.model.Role;
import org.ticketria.model.User;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeignAuthUserResponse {

    private Long userId;
    private String email;
    private boolean isActive;
    private Set<Role> roles;
    private String password;

    public FeignAuthUserResponse(User user) {
        setUserId(user.getId());
        setActive(user.isActive());
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setRoles(user.getRoles());
    }
}