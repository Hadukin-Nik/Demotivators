package com.demotivators.site.controllers;

import com.demotivators.site.dto.UserRegisterDTO;
import com.demotivators.site.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController()
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping(consumes = "application/json")
    public void registerUser(@RequestBody UserRegisterDTO userRegisterDTO){
        userService.createUser(userRegisterDTO);
    }
    @GetMapping("/table")
    public void showUsersTable() {}


}





