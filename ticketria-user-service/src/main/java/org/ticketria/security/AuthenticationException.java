package org.ticketria.security;

import org.springframework.context.i18n.LocaleContextHolder;
import org.ticketria.messages.CustomMessageSource;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException(){
        super(CustomMessageSource.getMessageForLocale("auth.invalid.credentials", LocaleContextHolder.getLocale()));
    }
    
}