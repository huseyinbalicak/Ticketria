package org.ticketria.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.ticketria.client.user.response.FeignAuthUserResponse;
import org.ticketria.dto.*;
import org.ticketria.service.AuthService;
import org.ticketria.token.JwtTokenService;

@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {
    private final AuthService authService;
    private final JwtTokenService jwtTokenService;

    public AuthController(AuthService authService, JwtTokenService jwtTokenService) {
        this.authService = authService;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/login")
    AuthResponse handleAuthentication(@Valid @RequestBody Credentials creds) {
        return authService.login(creds);
    }


    @PostMapping("/register")
    GenericMessage register(@Valid @RequestBody UserCreate request)
    {
        authService.createUser(request);
        return new GenericMessage("Hesabınızı aktifleştirmek için mail adresinizi kontrol ediniz.");
    }




    @GetMapping("/verify-token")
    public FeignAuthUserResponse verifyToken(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {

        return jwtTokenService.verifyToken(authorizationHeader);
    }

}
