package org.ticketria.exception;

import org.springframework.context.i18n.LocaleContextHolder;
import org.ticketria.messages.CustomMessageSource;

public class MaxTicketsPerCorporateExceededException extends RuntimeException {
    public MaxTicketsPerCorporateExceededException(){
        super(CustomMessageSource.getMessageForLocale("error.corporate.max.tickets.exceeded", LocaleContextHolder.getLocale()));
    }
}
