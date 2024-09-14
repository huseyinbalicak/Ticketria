package org.ticketria.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ticketria.client.user.response.UserResponse;
import org.ticketria.client.user.service.UserClientService;
import org.ticketria.config.PasswordUtils;
import org.ticketria.dto.AuthResponse;
import org.ticketria.dto.Credentials;
import org.ticketria.dto.UserAuthInfo;
import org.ticketria.dto.UserCreate;
import org.ticketria.exception.AuthenticationException;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserClientService userClientService;
    private final JwtService jwtService;
    private final RedisService redisService;

    public AuthResponse login(Credentials creds) {

        UserAuthInfo userResponse = userClientService.getUserByEmail(creds.email());

        if (userResponse == null) throw new AuthenticationException("Kullanıcı bulunamadı");

        if (!PasswordUtils.generateHashedPassword(creds.password()).equals(userResponse.getPassword()))
        {
            throw new AuthenticationException("Kullanıcı bilgileri yanlış");
        }

        String token = jwtService.generateToken(userResponse);

        try {
            redisService.set(userResponse.getId(), token);
        } catch (Exception e) {
            e.printStackTrace();
        }



        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setUserResponse(new UserResponse(userResponse));

        return authResponse;

    }

    public void register(UserCreate userCreate) {


        userClientService.createUser(userCreate);
    }
}
