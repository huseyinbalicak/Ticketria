package org.ticketria.exception;

import org.springframework.context.i18n.LocaleContextHolder;
import org.ticketria.messages.CustomMessageSource;

import java.util.Collections;
import java.util.Map;

public class NotUniqueUsernameException extends RuntimeException{
    public NotUniqueUsernameException() {
        super(CustomMessageSource.getMessageForLocale("error.validation", LocaleContextHolder.getLocale()));
    }

    public Map<String, String> getValidationErrors(){
        return Collections.singletonMap("username", CustomMessageSource.getMessageForLocale("username.constraint.UniqueUsername.message", LocaleContextHolder.getLocale()));
    }
}