package com.demotivators.site.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserRegisterDTO {
    private String login;
    private String password;

    public UserRegisterDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
