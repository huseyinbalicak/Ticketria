package org.ticketria.exception;

import org.springframework.context.i18n.LocaleContextHolder;
import org.ticketria.messages.CustomMessageSource;

public class EmailActivationException extends RuntimeException {
    public EmailActivationException() {
        super(CustomMessageSource.getMessageForLocale("error.send.email.toUser", LocaleContextHolder.getLocale()));
    }
}
