package org.ticketria.model;


import jakarta.persistence.*;
import lombok.*;
import org.ticketria.model.enums.Role;
import org.ticketria.model.enums.UserType;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "api_user")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private boolean active = false;

    private String activationToken;
    private String image;

    private String passwordResetToken;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Enumerated(EnumType.STRING)
    private Role role;

}
