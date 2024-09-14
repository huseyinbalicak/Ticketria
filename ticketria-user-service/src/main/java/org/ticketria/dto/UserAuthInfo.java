package org.ticketria.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ticketria.model.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthInfo {
    private String id;
    private String email;
    private String password;
    private String role;

    public UserAuthInfo(User user) {

        setId(String.valueOf(user.getId()));
        setRole(String.valueOf(user.getRole()));
        setEmail(user.getEmail());
        setPassword(user.getPassword());
    }
}