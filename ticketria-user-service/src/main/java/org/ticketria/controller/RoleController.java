package org.ticketria.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.ticketria.generic.GenericResponse;
import org.ticketria.messages.GenericMessage;
import org.ticketria.model.Role;
import org.ticketria.service.RoleService;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/users/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public GenericMessage createRole(@RequestBody Role role)
    {
        roleService.createRole(role);
        return new GenericMessage("Rol eklendi");
    }


    @PutMapping("add-role/{userId}/{role}")
    public GenericMessage addRoleToUser(@PathVariable Long userId, @PathVariable String role)
    {
        roleService.addRoleToUser(userId,role);
        return new GenericMessage("Kullanıcıya yeni rol atandı");
    }

    @GetMapping("/{userId}")
    public GenericResponse<Set<Role>> getUserRoles(@PathVariable Long userId)
    {
        return GenericResponse.success(roleService.getUserRoles(userId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/{role}")
    public GenericMessage deleteRoleToUser(@PathVariable Long id,@PathVariable String role)
    {
        roleService.deleteRoleToUser(id,role);
        return new GenericMessage("Kullanıcıdan rol silindi");
    }
}
