package com.demotivators.site.dto;

public class ErrorDTO {
    private final String message;

    public String getMessage() {
        return message;
    }

    public ErrorDTO(String message) {
        this.message = message;
    }
}
