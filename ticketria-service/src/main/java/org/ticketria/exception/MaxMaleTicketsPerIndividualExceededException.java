package org.ticketria.exception;

import org.springframework.context.i18n.LocaleContextHolder;
import org.ticketria.messages.CustomMessageSource;

public class MaxMaleTicketsPerIndividualExceededException extends RuntimeException{

    public MaxMaleTicketsPerIndividualExceededException(){
        super(CustomMessageSource.getMessageForLocale("error.individual.max.male.tickets.exceeded", LocaleContextHolder.getLocale()));
    }
}
