package com.demotivators.site.services.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserRegistrationValidationException extends RuntimeException {
    private final String validationErrorMessage;
}
