package org.ticketria.service;

public interface JwtService {
    String extractId(String jwt);

    String extractEmail(String jwt);

    boolean validateToken(String jwt, String comparedJwtInRedis);

    String extractRole(String jwt);
}