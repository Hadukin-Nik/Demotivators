package com.demotivators.site.models;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Objects;

@Data
@RequiredArgsConstructor
public class User {

    private final String login;
    private final String password;

    private Long id;

    @Bean
    @Scope("singleton")
    public User personSingleton() {
        return new User("", "");
    }
}
