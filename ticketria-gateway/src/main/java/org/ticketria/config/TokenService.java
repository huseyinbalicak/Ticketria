package org.ticketria.config;

public interface TokenService {

     FeignAuthUserResponse verifyToken(String authorizationHeader);

}