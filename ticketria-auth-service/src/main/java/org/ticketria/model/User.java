package org.ticketria.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User  {


    private long id;
    private String username;
    private String email;
    private String password;
    private Boolean isActive;
    private Set<Role> roles;

}