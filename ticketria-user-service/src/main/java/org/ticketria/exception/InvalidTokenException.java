package org.ticketria.exception;

import org.springframework.context.i18n.LocaleContextHolder;
import org.ticketria.messages.CustomMessageSource;

public class InvalidTokenException extends RuntimeException{

    public InvalidTokenException(){
        super(CustomMessageSource.getMessageForLocale("activate.user.invalid.token", LocaleContextHolder.getLocale()));
    }

}