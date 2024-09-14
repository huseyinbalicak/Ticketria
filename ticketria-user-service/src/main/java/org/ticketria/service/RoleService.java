package org.ticketria.service;

import org.springframework.stereotype.Service;
import org.ticketria.model.User;
import org.ticketria.model.enums.Role;
import org.ticketria.repository.UserRepository;

@Service
public class RoleService {

    private final UserRepository userRepository;

    public RoleService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void addRoleToUser(Long userId, Role role) {
        User foundUser = userRepository.getReferenceById(userId);
        foundUser.setRole(role);
        userRepository.save(foundUser);
    }

    public void deleteRoleFromUser(Long userId) {
        User foundUser = userRepository.getReferenceById(userId);
        foundUser.setRole(null);
        userRepository.save(foundUser);
    }

    public Role getUserRole(Long userId) {
        User foundUser = userRepository.getReferenceById(userId);
        return foundUser.getRole();
    }

    public void addDefaultRole(User user) {
        if(user.getEmail().equals("ftteknoloji@gmail.com")) {
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }
        userRepository.save(user);
    }
}
