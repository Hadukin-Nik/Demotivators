package com.demotivators.site.dto;

import com.demotivators.site.validation.StrongPassword;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @Size(min = 5, max = 15, message = "Login must be between 5 and 15 characters")
    @NotNull(message = "Login cannot be null")
    private String login;

    @StrongPassword
    @NotNull(message = "Password cannot be null")
    private String password;
}
