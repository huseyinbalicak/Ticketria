package org.ticketria.exception;

import org.springframework.context.i18n.LocaleContextHolder;
import org.ticketria.messages.CustomMessageSource;

public class RoleException extends RuntimeException {

    public RoleException(){
        super(CustomMessageSource.getMessageForLocale("error.role.exists", LocaleContextHolder.getLocale()));
    }
}
