package org.ticketria.exception;

import org.springframework.context.i18n.LocaleContextHolder;
import org.ticketria.messages.CustomMessageSource;

public class TripNotFoundException extends RuntimeException{

    public TripNotFoundException(String  tripNumber){
        super(CustomMessageSource.getMessageForLocale("trip.not.found", LocaleContextHolder.getLocale(), tripNumber));
    }
}
