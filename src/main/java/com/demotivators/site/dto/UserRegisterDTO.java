package com.demotivators.site.dto;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
