package org.ticketria.token;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.ticketria.client.user.response.FeignAuthUserResponse;
import org.ticketria.dto.Credentials;
import org.ticketria.model.Role;


import javax.crypto.SecretKey;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Primary
public class JwtTokenService implements TokenService{


    SecretKey secretKey=Keys.hmacShaKeyFor("secret-must-be-at-least-32-chars".getBytes());
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public Token createToken(FeignAuthUserResponse user, Credentials creds) {

        Set<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
        TokenSubject tokenSubject = new TokenSubject(user.getUserId(), user.isActive(), roles);
        try {
            String subject = mapper.writeValueAsString(tokenSubject);
            String token = Jwts.builder().setSubject(subject).signWith(secretKey).compact();

            return new Token("Bearer", token);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public FeignAuthUserResponse verifyToken(String authorizationHeader) {
        if(authorizationHeader == null) return null;
        var token = authorizationHeader.split(" ")[1];
        JwtParser parser = Jwts.parser().setSigningKey(secretKey).build();

        try {

            Jws<Claims> claims = parser.parseClaimsJws(token);
            var subject = claims.getBody().getSubject();
            var tokenSubject = mapper.readValue(subject, TokenSubject.class);


            FeignAuthUserResponse user = new FeignAuthUserResponse();

            user.setUserId(tokenSubject.id());
            user.setActive(tokenSubject.active());
            Set<Role> roles = tokenSubject.roles().stream()
                    .map(roleName -> {
                        Role role = new Role();
                        role.setName(roleName);
                        return role;
                    })
                    .collect(Collectors.toSet());
            user.setRoles(roles);

            return user;
        } catch (JwtException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public record TokenSubject(long id, boolean active, Set<String> roles)
    {

    }
}