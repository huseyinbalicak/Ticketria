package org.ticketria.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.ticketria.generic.GenericResponse;
import org.ticketria.messages.GenericMessage;
import org.ticketria.model.enums.Role;
import org.ticketria.service.RoleService;

@RestController
@RequestMapping("/api/v1/users/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PutMapping("add-role/{userId}/{role}")
    public GenericMessage addRoleToUser(@PathVariable Long userId, @PathVariable Role role) {
        roleService.addRoleToUser(userId, role);
        return new GenericMessage("Kullanıcıya yeni rol atandı");
    }

    @GetMapping("/{userId}")
    public GenericResponse<Role> getUserRole(@PathVariable Long userId) {
        return GenericResponse.success(roleService.getUserRole(userId), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public GenericMessage deleteRoleFromUser(@PathVariable Long userId) {
        roleService.deleteRoleFromUser(userId);
        return new GenericMessage("Kullanıcıdan rol silindi");
    }
}
