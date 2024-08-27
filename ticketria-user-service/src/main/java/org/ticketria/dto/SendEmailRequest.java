package org.ticketria.dto;

public record SendEmailRequest(
        String email, String activationToken
){
}
