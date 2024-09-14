package org.ticketria.client.user.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ticketria.client.user.UserClient;
import org.ticketria.dto.GenericMessage;
import org.ticketria.dto.UserAuthInfo;
import org.ticketria.dto.UserCreate;

@Service
@RequiredArgsConstructor
public class UserClientService {

    private final UserClient userClient;

    public UserAuthInfo getUserByEmail(String email){
        return userClient.getUserByEmail(email);
    }

    public GenericMessage createUser(UserCreate request)
    {
        return userClient.createUser(request);
    }
}