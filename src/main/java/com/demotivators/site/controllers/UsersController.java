package com.demotivators.site.controllers;

import com.demotivators.site.dto.UserDTO;
import com.demotivators.site.models.User;
import com.demotivators.site.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;

    @PostMapping(consumes = "application/json")
    public User add(@RequestBody UserDTO userDTO){
        return userService.create(userDTO);
    }
    @GetMapping("/table")
    public void showUsersTable() {}


}





