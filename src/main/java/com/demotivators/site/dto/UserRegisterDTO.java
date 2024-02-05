package com.demotivators.site.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {
    private String login;
    private String password;
}
