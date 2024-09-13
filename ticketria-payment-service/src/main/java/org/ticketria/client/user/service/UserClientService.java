package org.ticketria.client.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ticketria.client.user.UserClient;
import org.ticketria.client.user.dto.response.UserResponse;

@Service
@RequiredArgsConstructor
public class UserClientService {

    private final UserClient userClient;

    public UserResponse getUserByEmail(String email){
        return userClient.getUserByEmail(email);
    }
}