package com.demotivators.site.models;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Data
public class User {
    private final String login;
    private final String password;

    private Long id;
}
