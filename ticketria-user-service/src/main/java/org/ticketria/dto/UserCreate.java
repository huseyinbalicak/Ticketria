package org.ticketria.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.ticketria.model.User;
import org.ticketria.model.enums.UserType;
import org.ticketria.validation.UniqueEmail;
import org.ticketria.validation.UniqueUsername;

public record UserCreate(

        @NotBlank(message = "{username.validation.constraints.NotBlank.message}")
        @UniqueUsername
        String username,
        @NotBlank
        @Email
        @UniqueEmail
        String email,
        @NotBlank
        @Size(min = 8, max = 255)
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{password.validation.constraints.pattern.message}")
        String password,

        UserType userType

) {

    public User toMapUser()
    {
        User user=new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        user.setUserType(userType);

        return user;
    }

}
