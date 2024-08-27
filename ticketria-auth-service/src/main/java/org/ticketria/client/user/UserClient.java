package org.ticketria.client.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.ticketria.client.user.response.FeignAuthUserResponse;
import org.ticketria.dto.GenericMessage;
import org.ticketria.dto.UserCreate;

@FeignClient(value = "ticketria-user-service", url = "localhost:8080/api/v1/users")
public interface UserClient {

    @GetMapping("/auth/{email}")
    FeignAuthUserResponse getUserByEmail(@PathVariable String email);

    @PostMapping
    GenericMessage createUser(@RequestBody UserCreate request);
}