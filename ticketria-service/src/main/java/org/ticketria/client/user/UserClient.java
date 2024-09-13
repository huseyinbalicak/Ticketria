package org.ticketria.client.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.ticketria.client.user.response.UserResponse;

@FeignClient(value = "ticketria-user-service", url = "localhost:8080/api/v1/users")
public interface UserClient {

    @GetMapping("/{email}")
    UserResponse getUserByEmail(@PathVariable String email);
}