package org.ticketria.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ticketria.client.user.response.FeignAuthUserResponse;
import org.ticketria.client.user.response.UserResponse;
import org.ticketria.client.user.service.UserClientService;
import org.ticketria.config.PasswordUtils;
import org.ticketria.dto.AuthResponse;
import org.ticketria.dto.Credentials;
import org.ticketria.dto.UserCreate;
import org.ticketria.exception.AuthenticationException;
import org.ticketria.token.Token;
import org.ticketria.token.TokenService;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserClientService userClientService;
    private final TokenService tokenService;

    public AuthResponse login(Credentials creds) {

        FeignAuthUserResponse userResponse = userClientService.getUserByEmail(creds.email());

        if (userResponse == null) throw new AuthenticationException("Kullanıcı bulunamadı");

        if (!PasswordUtils.generateHashedPassword(creds.password()).equals(userResponse.getPassword()))
        {
            throw new AuthenticationException("Kullanıcı bilgileri yanlış");
        }


       /* if(!user.isActive())
            throw new UserNotActiveException();*/

        Token token = tokenService.createToken(userResponse,creds);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setUserResponse(new UserResponse(userResponse));

        return authResponse;

    }

    public void createUser(UserCreate userCreate) {


        userClientService.createUser(userCreate);
    }
}
