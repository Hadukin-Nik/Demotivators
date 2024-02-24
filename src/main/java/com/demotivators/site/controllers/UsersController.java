package com.demotivators.site.controllers;

import com.demotivators.site.dao.UserDAOImpl;
import com.demotivators.site.dto.UserDTO;
import com.demotivators.site.models.User;
import com.demotivators.site.models.UserAuthData;
import com.demotivators.site.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;
    private final UserDAOImpl dao;

    @PostMapping(value = "/registration", consumes = "application/json")
    public User add(@RequestBody UserDTO userDTO){
        return userService.create(userDTO);
    }
    @PostMapping(value = "/auth", consumes = "application/json")
    public User auth(@RequestBody UserDTO userDTO){
        return null;
    }
    @GetMapping("/table")
    public void showUsersTable() {

    }
}





