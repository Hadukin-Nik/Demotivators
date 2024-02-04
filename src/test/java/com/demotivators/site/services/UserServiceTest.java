package com.demotivators.site.services;

import com.demotivators.site.dao.UserDAO;
import com.demotivators.site.dto.UserRegisterDTO;
import com.demotivators.site.models.User;
import com.demotivators.site.services.exceptions.UserDuplicateException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.dao.DuplicateKeyException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UserServiceTest {
    private final UserRegisterDTO testUser1 = new UserRegisterDTO("123", "123");
    @Test
    void createUser_Happy_Path() {
        UserDAO userDAO = new UserDAO() {
            @Override
            public User addUser(UserRegisterDTO userDTO) {
                return null;
            }
        };

        UserService userService = new UserService(userDAO);

        assertNull(userService.createUser(testUser1));
    }

    @Test
    void createUser_Duplicate_name() {
        UserDAO userDAO = new UserDAO() {
            @Override
            public User addUser(UserRegisterDTO userDTO) {
                throw new DuplicateKeyException("");
            }
        };

        UserService userService = new UserService(userDAO);

        assertThrows(UserDuplicateException.class, () -> userService.createUser(testUser1));
    }
}