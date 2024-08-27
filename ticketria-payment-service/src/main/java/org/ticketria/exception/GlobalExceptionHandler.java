package org.ticketria.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.ticketria.dto.response.GenericResponseConstants;
import org.ticketria.messages.CustomMessageSource;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request)
    {

        ApiError apiError=new ApiError();
        apiError.setPath(request.getRequestURI());
        String message= CustomMessageSource.getMessageForLocale("error.validation", LocaleContextHolder.getLocale());

        apiError.setMessage(message);
        apiError.setStatus(GenericResponseConstants.FAILED);
        apiError.setStatusCode(400);
        Map<String, String> validationErrors=new HashMap<>();
        for(var fieldError:exception.getBindingResult().getFieldErrors())
        {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        apiError.setValidationErrors(validationErrors);
        return ResponseEntity.badRequest().body(apiError);
    }

}
