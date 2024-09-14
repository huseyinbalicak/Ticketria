package org.ticketria.service;

import org.ticketria.dto.UserAuthInfo;

public interface JwtService {

    String generateToken(UserAuthInfo userAuthInfo);
}