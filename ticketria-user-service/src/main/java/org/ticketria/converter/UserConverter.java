package org.ticketria.converter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.ticketria.dto.UserResponse;
import org.ticketria.model.User;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserConverter {

    public static UserResponse toResponse(User user) {
        return new UserResponse(user);
    }

    public static List<UserResponse> toResponse(List<User> users) {
        return users.stream().map(UserConverter::toResponse).toList();
    }
}