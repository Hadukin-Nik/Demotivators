package com.demotivators.site.controllers;

import com.demotivators.site.dto.UserRegisterDTO;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/users")
public class UsersController {

    @PostMapping(value = "/registration", consumes = "application/json")
    public void registerUser(@RequestBody UserRegisterDTO userRegisterDTO){
        System.out.println(userRegisterDTO);
    }
    @GetMapping("/table")
    public void showUsersTable() {}


}




