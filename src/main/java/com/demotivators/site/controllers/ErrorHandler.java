package com.demotivators.site.controllers;

import com.demotivators.site.dto.ErrorDTO;
import com.demotivators.site.services.exceptions.UserDuplicateException;
import com.demotivators.site.services.exceptions.UserRegistrationValidationException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(UserDuplicateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleCustomException(UserDuplicateException ce) {
        return new ErrorDTO("User with such login is already exists");
    }

    @ExceptionHandler(UserRegistrationValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleCustomException(UserRegistrationValidationException ce) {
        return new ErrorDTO(ce.getValidationErrorMessage());
    }
}
