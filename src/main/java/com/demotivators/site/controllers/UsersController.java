package com.demotivators.site.controllers;

import com.demotivators.site.dto.UserRegisterDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

@PostMapping(value = "/users", consumes = "application/json")
    public void registerUser(@RequestBody UserRegisterDTO userRegisterDTO){
        System.out.println(userRegisterDTO);
    }
}
