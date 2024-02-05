package com.demotivators.site.controllers;

import com.demotivators.site.dto.UserDTO;
import com.demotivators.site.models.User;
import com.demotivators.site.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping(consumes = "application/json")
    public User add(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }
    @GetMapping("/table")
    public void showUsersTable() {}


}





