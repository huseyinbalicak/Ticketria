package org.ticketria.client.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.ticketria.client.user.dto.response.FeignClientUserResponse;

@FeignClient(value = "ticketria-user-service", url = "localhost:8080/api/v1/users/feign")
public interface UserClient {

    @GetMapping("/{email}")
    FeignClientUserResponse getUserByEmail(@PathVariable String email);
}