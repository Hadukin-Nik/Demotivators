package com.demotivators.site.dto;

public class UserRegisterDTO {
    private String nickname;
    private String password;

    public UserRegisterDTO(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserRegisterDTO{" +
                "nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
