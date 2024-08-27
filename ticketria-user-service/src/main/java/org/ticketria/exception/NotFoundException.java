package org.ticketria.exception;

import org.springframework.context.i18n.LocaleContextHolder;
import org.ticketria.messages.CustomMessageSource;

public class NotFoundException extends RuntimeException {
    
    public NotFoundException(long id){
        super(CustomMessageSource.getMessageForLocale("user.not.found", LocaleContextHolder.getLocale(), id));
    }
}