package com.demotivators.site.validation;
import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = StrongPasswordValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StrongPassword {
    String message() default "Must be size from 5 to 15 and have at least one lowercase letter and number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}