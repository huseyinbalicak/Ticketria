package org.ticketria.messages;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class CustomMessageSource {

    public static String getMessageForLocale(String messageKey, Locale locale)
    {
        return ResourceBundle.getBundle("messages", locale).getString(messageKey);
    }

    public static String getMessageForLocale(String messageKey, Locale locale, Object... arguments) {
        String message = getMessageForLocale(messageKey, locale);
        return MessageFormat.format(message, arguments);
    }
}