package org.ticketria.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ticketria.dto.AuthResponse;
import org.ticketria.dto.Credentials;
import org.ticketria.dto.GenericMessage;
import org.ticketria.dto.UserCreate;
import org.ticketria.service.AuthService;

@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {
    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;

    }

    @PostMapping("/login")
    AuthResponse handleAuthentication(@Valid @RequestBody Credentials creds) {
        return authService.login(creds);
    }


    @PostMapping("/register")
    GenericMessage register(@Valid @RequestBody UserCreate request)
    {
        authService.register(request);
        return new GenericMessage("Hesabınızı aktifleştirmek için mail adresinizi kontrol ediniz.");
    }

}
