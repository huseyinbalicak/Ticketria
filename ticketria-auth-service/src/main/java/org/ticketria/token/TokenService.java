package org.ticketria.token;

import org.ticketria.client.user.response.FeignAuthUserResponse;
import org.ticketria.dto.Credentials;

public interface TokenService {

     Token createToken(FeignAuthUserResponse user, Credentials creds);

     FeignAuthUserResponse verifyToken(String authorizationHeader);

}