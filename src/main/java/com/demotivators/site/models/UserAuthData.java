package com.demotivators.site.models;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

@Data
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class UserAuthData {
    private final String token;

    private String login;
}
