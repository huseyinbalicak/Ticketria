package org.ticketria.error;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.ticketria.exception.*;
import org.ticketria.messages.CustomMessageSource;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request)
    {

        ApiError apiError=new ApiError();
        apiError.setPath(request.getRequestURI());
        String message= CustomMessageSource.getMessageForLocale("error.validation", LocaleContextHolder.getLocale());

        apiError.setMessage(message);
        apiError.setStatusCode(400);
        Map<String, String> validationErrors=new HashMap<>();
        for(var fieldError:exception.getBindingResult().getFieldErrors())
        {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        apiError.setValidationErrors(validationErrors);
        return ResponseEntity.badRequest().body(apiError);
    }


    @ExceptionHandler(NotUniqueEmailException.class)
    ResponseEntity<ApiError> handleNotUniqueEmailException(NotUniqueEmailException exception)
    {
        ApiError apiError=new ApiError();
        apiError.setPath("/api/v1/users");
        apiError.setMessage(exception.getMessage());
        apiError.setStatusCode(400);
        apiError.setValidationErrors(exception.getValidationErrors());
        return ResponseEntity.badRequest().body(apiError);
    }


    @ExceptionHandler(NotUniqueUsernameException.class)
    ResponseEntity<ApiError> handleNotUniqueUsernameException(NotUniqueUsernameException exception)
    {
        ApiError apiError=new ApiError();
        apiError.setPath("/api/v1/users");
        apiError.setMessage(exception.getMessage());
        apiError.setStatusCode(400);
        apiError.setValidationErrors(exception.getValidationErrors());
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(EmailActivationException.class)
    ResponseEntity<ApiError> handleEmailActivationException(EmailActivationException exception)
    {
        ApiError apiError=new ApiError();
        apiError.setPath("/api/v1/users");
        apiError.setMessage(exception.getMessage());
        apiError.setStatusCode(502);
        return ResponseEntity.status(502).body(apiError);
    }

    @ExceptionHandler(InvalidTokenException.class)
    ResponseEntity<ApiError> handleInvalidTokenException(InvalidTokenException exception, HttpServletRequest request)
    {
        ApiError apiError=new ApiError();
        apiError.setPath(request.getRequestURI());
        apiError.setMessage(exception.getMessage());
        apiError.setStatusCode(400);
        return ResponseEntity.status(400).body(apiError);
    }

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ApiError> handleEntityNotFoundException(NotFoundException exception, HttpServletRequest request)
    {
        ApiError apiError=new ApiError();
        apiError.setPath(request.getRequestURI());
        apiError.setMessage(exception.getMessage());
        apiError.setStatusCode(404);
        return ResponseEntity.status(404).body(apiError);
    }

    @ExceptionHandler(UserNotActiveException.class)
    ResponseEntity<ApiError> handleUserNotActiveException(UserNotActiveException exception)
    {
        ApiError apiError=new ApiError();
        apiError.setPath("/api/v1/auth");
        apiError.setMessage(exception.getMessage());
        apiError.setStatusCode(401);
        return ResponseEntity.status(401).body(apiError);
    }


    @ExceptionHandler(RoleException.class)
    ResponseEntity<ApiError> handleRoleException(RoleException exception)
    {
        ApiError apiError=new ApiError();
        apiError.setPath("/api/v1/users/roles");
        apiError.setMessage(exception.getMessage());
        apiError.setStatusCode(400);
        return ResponseEntity.status(400).body(apiError);
    }

}
