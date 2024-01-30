package com.demotivators.site.controllers;

import com.demotivators.site.dto.UserRegisterDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/hello")
public class HelloController {
    @GetMapping()
    public void showLogInPage() {

    }

    @PostMapping(consumes =  "application/json")
    public void authenticateUser(@RequestBody UserRegisterDTO userRegisterDTO) {

    }

}