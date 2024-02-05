package com.demotivators.site.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return (value.matches("^(?=.*\\d)(?=.*[a-zA-Z]).*$") && value.length() >= 5 && value.length() <= 15);
    }
}