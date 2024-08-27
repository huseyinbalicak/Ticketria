package org.ticketria.controller;


import jakarta.validation.Valid;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;
import org.ticketria.converter.UserConverter;
import org.ticketria.dto.*;
import org.ticketria.generic.GenericMessage;
import org.ticketria.messages.CustomMessageSource;
import org.ticketria.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public GenericMessage createUser(@Valid @RequestBody UserCreate userCreate)
    {
        userService.save(userCreate.toMapUser());
        String message= CustomMessageSource.getMessageForLocale("create.user.success.message", LocaleContextHolder.getLocale());
        return new GenericMessage(message);
    }

    @GetMapping
    public List<UserResponse> listUsers()
    {
        return UserConverter.toResponse(userService.listUsers());
    }

    @GetMapping("/{id}")
    UserResponse getUserById(@PathVariable long id){
        return new UserResponse(userService.getUser(id));
    }

    @GetMapping("email/{email}")
    UserResponse getUserByEmail(@PathVariable String email)
    {
        return new UserResponse(userService.findByEmail(email));
    }

    @GetMapping("/feign/{email}")
    FeignClientUserResponse findUserByEmail(@PathVariable String email)
    {
        return new FeignClientUserResponse(userService.findByEmail(email));
    }

    @GetMapping("/auth/{email}")
    FeignAuthUserResponse findUserByAuthEmail(@PathVariable String email)
    {
        return new FeignAuthUserResponse(userService.findByEmail(email));
    }

    @PatchMapping("/activate/{token}")
    public GenericMessage activateUser(@PathVariable String token)
    {
        userService.activateUser(token);
        String message= CustomMessageSource.getMessageForLocale("activate.user.success.message",LocaleContextHolder.getLocale());
        return new GenericMessage(message);
    }

    @PutMapping("/{id}")
    UserResponse updateUser(@PathVariable long id, @Valid @RequestBody UserUpdate userUpdate){
        return new UserResponse(userService.updateUser(id, userUpdate));
    }

    @DeleteMapping("/{id}")
    GenericMessage deleteUser(@PathVariable long id){
        userService.deleteUser(id);
        return new GenericMessage("User is deleted");
    }
}
