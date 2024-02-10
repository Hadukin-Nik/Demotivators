package com.demotivators.site.controllers;

import com.demotivators.site.controllers.filter.Token;
import com.demotivators.site.dao.UserDAO;
import com.demotivators.site.dao.UserDAOImpl;
import com.demotivators.site.dto.MemeDTO;
import com.demotivators.site.dto.UserDTO;
import com.demotivators.site.models.User;
import com.demotivators.site.models.UserAuthData;
import com.demotivators.site.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;
    private final UserDAOImpl dao;
    private final Token token;

    @PostMapping(value = "/auth", consumes = "application/json")
    public User add(@RequestBody UserDTO userDTO){
        return userService.create(userDTO);
    }

    @GetMapping("/table")
    public void showUsersTable() {
        UserAuthData userAuthData = token.getUserAuthData();
        System.out.println(userAuthData != null ? userAuthData.getLogin() : "Pshel nahui");
    }
}





