package com.demotivators.site.models;

import lombok.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@Data
@RequiredArgsConstructor
public class User {
    private final String login;
    private final String password;

    private String token;

    private Long id;

    private Date creation_date;

    public User(UserDataBase userDataBase) {
        this.login = userDataBase.getLogin();
        this.password = userDataBase.getPassword();
        this.token = userDataBase.getToken();

        this.id = Long.parseLong(userDataBase.getId());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);


        try {
            this.creation_date = formatter.parse(userDataBase.getCreation_date());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
