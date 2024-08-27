package org.ticketria.exception;

import org.springframework.context.i18n.LocaleContextHolder;
import org.ticketria.messages.CustomMessageSource;

public class MaxTicketsPerIndividualExceededException extends RuntimeException {
    public MaxTicketsPerIndividualExceededException(){
        super(CustomMessageSource.getMessageForLocale("error.individual.max.tickets.exceeded", LocaleContextHolder.getLocale()));
    }
}