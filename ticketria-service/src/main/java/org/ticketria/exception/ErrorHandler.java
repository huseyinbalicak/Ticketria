package org.ticketria.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
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



    @ExceptionHandler(TripNotFoundException.class)
    ResponseEntity<ApiError> handleTripNotFoundException(TripNotFoundException exception, HttpServletRequest request)
    {
        ApiError apiError=new ApiError();
        apiError.setPath(request.getRequestURI());
        apiError.setMessage(exception.getMessage());
        apiError.setStatusCode(404);
        return ResponseEntity.status(404).body(apiError);
    }

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException exception, HttpServletRequest request)
    {
        ApiError apiError=new ApiError();
        apiError.setPath(request.getRequestURI());
        apiError.setMessage(exception.getMessage());
        apiError.setStatusCode(404);
        return ResponseEntity.status(404).body(apiError);
    }

    @ExceptionHandler(MaxTicketsPerIndividualExceededException.class)
    ResponseEntity<ApiError> handleMaxTicketsPerIndividualExceededException(MaxTicketsPerIndividualExceededException exception, HttpServletRequest request)
    {
        ApiError apiError=new ApiError();
        apiError.setPath(request.getRequestURI());
        apiError.setMessage(exception.getMessage());
        apiError.setStatusCode(400);
        return ResponseEntity.status(400).body(apiError);
    }


    @ExceptionHandler(MaxMaleTicketsPerIndividualExceededException.class)
    ResponseEntity<ApiError> handleMaxMaleTicketsPerIndividualExceededException(MaxMaleTicketsPerIndividualExceededException exception, HttpServletRequest request)
    {
        ApiError apiError=new ApiError();
        apiError.setPath(request.getRequestURI());
        apiError.setMessage(exception.getMessage());
        apiError.setStatusCode(400);
        return ResponseEntity.status(400).body(apiError);
    }

    @ExceptionHandler(MaxTicketsPerCorporateExceededException.class)
    ResponseEntity<ApiError> handleMaxTicketsPerCorporateExceededException(MaxTicketsPerCorporateExceededException exception, HttpServletRequest request)
    {
        ApiError apiError=new ApiError();
        apiError.setPath(request.getRequestURI());
        apiError.setMessage(exception.getMessage());
        apiError.setStatusCode(400);
        return ResponseEntity.status(400).body(apiError);
    }

    @ExceptionHandler(DuplicateSeatNumberException.class)
    ResponseEntity<ApiError> handleDuplicateSeatNumberException(DuplicateSeatNumberException exception, HttpServletRequest request)
    {
        ApiError apiError=new ApiError();
        apiError.setPath(request.getRequestURI());
        apiError.setMessage(exception.getMessage());
        apiError.setStatusCode(400);
        return ResponseEntity.status(400).body(apiError);
    }
}
