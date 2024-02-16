package com.demotivators.site.models;

import lombok.Data;

import java.util.Date;

@Data
public class UserDataBase {
    private String login;
    private String password;

    private String token;

    private String id;

    private String creation_date;
}
