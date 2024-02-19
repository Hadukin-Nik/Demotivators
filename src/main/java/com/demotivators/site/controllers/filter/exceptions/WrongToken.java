package com.demotivators.site.controllers.filter.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class WrongToken extends RuntimeException implements FilterError{
    private final String message = "Unauthorized person";
}
