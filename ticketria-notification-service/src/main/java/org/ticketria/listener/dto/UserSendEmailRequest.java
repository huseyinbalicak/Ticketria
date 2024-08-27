package org.ticketria.listener.dto;

public record UserSendEmailRequest(
        String email, String activationToken
){
}
