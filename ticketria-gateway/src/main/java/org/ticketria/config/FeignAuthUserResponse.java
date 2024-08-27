package org.ticketria.config;

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
    private String username;
    private boolean isActive;
    private Set<Role> roles;
}