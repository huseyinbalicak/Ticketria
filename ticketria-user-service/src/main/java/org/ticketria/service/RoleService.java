package org.ticketria.service;

import org.springframework.stereotype.Service;
import org.ticketria.exception.RoleException;
import org.ticketria.model.Role;
import org.ticketria.model.User;
import org.ticketria.repository.RoleRepository;
import org.ticketria.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public RoleService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public void createRole(Role role)
    {
        Role foundRole=roleRepository.findByName(role.getName());
        if(foundRole!=null)
        {
            throw new RoleException();
        }

       roleRepository.save(role);
    }

    public void addRoleToUser(Long userId,String role)
    {
        User foundUser=userRepository.getReferenceById(userId);
        Role foundRole=roleRepository.findByName(role);

        Set<Role> roles = foundUser.getRoles();
        roles.add(foundRole);

        foundUser.setRoles(roles);

        userRepository.save(foundUser);
    }

    public void deleteRoleToUser(Long userId,String role)
    {
        User foundUser=userRepository.getReferenceById(userId);
        Role foundRole=roleRepository.findByName(role);

        Set<Role> roles = foundUser.getRoles();

        roles.remove(foundRole);
        userRepository.save(foundUser);
    }


    public Set<Role> getUserRoles(Long userId)
    {
        User foundUser=userRepository.getReferenceById(userId);

        return foundUser.getRoles();

    }

    public void addDefaultRole(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");

        Set<Role> roleSet = new HashSet<>();

        if(user.getEmail().equals("balicakhuseyin@gmail.com")) {
            roleSet.add(roleAdmin);
        }

        roleSet.add(roleUser);
        user.setRoles(roleSet);
        userRepository.save(user);
    }

}
