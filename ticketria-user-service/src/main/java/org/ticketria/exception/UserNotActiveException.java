package org.ticketria.exception;

import org.springframework.context.i18n.LocaleContextHolder;
import org.ticketria.messages.CustomMessageSource;

public class UserNotActiveException extends RuntimeException {

    public UserNotActiveException(){
        super(CustomMessageSource.getMessageForLocale("user.not.active.error", LocaleContextHolder.getLocale()));
    }
}
