package com.demotivators.site.services;

import com.demotivators.site.dao.UserDAO;
import com.demotivators.site.dto.UserDTO;
import com.demotivators.site.models.User;
import com.demotivators.site.services.exceptions.UserDuplicateException;
import com.demotivators.site.services.exceptions.UserRegistrationValidationException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DuplicateKeyException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UserServiceTest {
    private final UserDTO testUser1 = new UserDTO("login", "password123");

    @Test
    void create_Happy_Path() {
        UserDAO userDAO = new UserDAO() {
            @Override
            public Long addUser(UserDTO userDTO) {
                return 1L;
            }
        };

        UserService userService = new UserService(userDAO);

        User expectedUser = new User(testUser1.getLogin(), testUser1.getPassword());
        expectedUser.setId(1L);

        assertEquals(expectedUser, userService.create(testUser1));
    }

    @Test
    void create_Duplicate_name() {
        UserDAO userDAO = new UserDAO() {
            @Override
            public Long addUser(UserDTO userDTO) {
                throw new DuplicateKeyException("");
            }
        };

        UserService userService = new UserService(userDAO);

        assertThrows(UserDuplicateException.class, () -> userService.create(testUser1));
    }

    @Test
    void create_invalid_login() {
        UserDTO testUser2 = new UserDTO("fuck", "password123");
        UserDAO userDAO = new UserDAO() {
            @Override
            public Long addUser(UserDTO userDTO) {
                return 1L;
            }
        };

        UserService userService = new UserService(userDAO);

        assertThrows(UserRegistrationValidationException.class, () -> userService.create(testUser2));
    }

    @Test
    void create_invalid_password_length() {
        UserDTO testUser2 = new UserDTO("login", "fuck");
        UserDAO userDAO = new UserDAO() {
            @Override
            public Long addUser(UserDTO userDTO) {
                return 1L;
            }
        };

        UserService userService = new UserService(userDAO);

        assertThrows(UserRegistrationValidationException.class, () -> userService.create(testUser2));
    }

    @Test
    void create_invalid_password_symbols() {
        UserDTO testUser2 = new UserDTO("login", "fucking_shit");
        UserDAO userDAO = new UserDAO() {
            @Override
            public Long addUser(UserDTO userDTO) {
                return 1L;
            }
        };

        UserService userService = new UserService(userDAO);

        assertThrows(UserRegistrationValidationException.class, () -> userService.create(testUser2));
    }
}