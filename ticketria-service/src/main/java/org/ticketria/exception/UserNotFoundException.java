package org.ticketria.exception;

import org.springframework.context.i18n.LocaleContextHolder;
import org.ticketria.messages.CustomMessageSource;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){
        super(CustomMessageSource.getMessageForLocale("user.not.found", LocaleContextHolder.getLocale()));
    }
}
