package com.demotivators.site.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDTO {
    private String login;
    private String password;

    public UserRegisterDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserRegisterDTO{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
