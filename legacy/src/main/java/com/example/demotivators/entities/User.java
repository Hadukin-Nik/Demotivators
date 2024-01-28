package com.example.demotivators.entities;

import com.example.demotivators.Config;

import java.util.Date;

import static com.example.demotivators.entities.User.Role.fromInteger;

public class User {
    public enum Role {
        Commoner, Admin, SuperAdmin;

        public static Role fromInteger(int x) {
            switch (x) {
                case 0:
                    return Commoner;
                case 1:
                    return Admin;
                case 2:
                    return SuperAdmin;
            }
            return null;
        }

        public static Integer toInt(Role x) {
            switch (x) {
                case Commoner:
                    return 0;
                case Admin:
                    return 1;
                case SuperAdmin:
                    return 2;
            }
            return -1;
        }
    }

    private String avatar;

    private String name;
    private String nickname;

    private String telephone_number;


    private Date date_registration;

    private Role role;

    private long userId;

    public User(String avatar, String name, String nickname, String telephoneNumber, Date dateRegistration, int role, long userId) {
        this.avatar = avatar;
        this.name = name;
        this.nickname = nickname;

        this.date_registration = dateRegistration;

        this.role = fromInteger(role);
        telephone_number = telephoneNumber;
        this.userId = userId;
    }

    public User(String avatar, String name, String nickname, String telephoneNumber) {
        this.avatar = avatar;
        this.name = name;
        this.nickname = nickname;

        this.date_registration = new Date(System.currentTimeMillis());

        this.role = fromInteger(0);
        telephone_number = telephoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        if (avatar == null) {
            return "images/" + Config.DEFAULT_PNG;
        } else if (!avatar.startsWith("images")) {
            return "images/" + avatar;
        } else {
            return avatar;
        }
    }

    public String getNickname() {
        return nickname;
    }

    public Date getDate_registration() {
        return date_registration;
    }

    public Role getRole() {
        return role;
    }

    public String getTelephone_number() {
        return telephone_number;
    }

    public long getUserId() {
        return userId;
    }

}
