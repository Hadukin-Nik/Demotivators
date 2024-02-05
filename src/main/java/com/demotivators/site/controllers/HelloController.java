package com.demotivators.site.controllers;

import com.demotivators.site.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/hello")
public class HelloController {
    @GetMapping
    public void showLogInPage() {

    }

    @PostMapping(consumes =  "application/json")
    public void authenticateUser(@RequestBody UserDTO userDTO) {

    }

}