package org.ticketria.exception;

import org.springframework.context.i18n.LocaleContextHolder;
import org.ticketria.messages.CustomMessageSource;

public class DuplicateSeatNumberException extends RuntimeException{

    public DuplicateSeatNumberException(int id){
        super(CustomMessageSource.getMessageForLocale("duplicate.seat.number", LocaleContextHolder.getLocale(),id));
    }
}
