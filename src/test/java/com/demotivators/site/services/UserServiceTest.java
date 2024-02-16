package com.demotivators.site.services;

import com.demotivators.site.dao.UserDAO;
import com.demotivators.site.dto.UserDTO;
import com.demotivators.site.models.User;
import com.demotivators.site.services.exceptions.UserDuplicateException;
import com.demotivators.site.services.exceptions.UserRegistrationValidationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DuplicateKeyException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UserServiceTest {
    private final UserDTO testUser1 = new UserDTO("login", "password123");
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private final UserDAO emptyDAO = new UserDAO() {
        @Override
        public Long addUser(UserDTO userDTO) {
            return 1L;
        }

        @Override
        public User getUserByToken(String token) {
            return null;
        }
    };

    @Test
    void create_Happy_Path() {
        UserService userService = new UserService(emptyDAO, validator);

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

            @Override
            public User getUserByToken(String token) {
                return null;
            }
        };

        UserService userService = new UserService(userDAO, validator);

        assertThrows(UserDuplicateException.class, () -> userService.create(testUser1));
    }

    @Test
    void create_invalid_login() {
        UserDTO testUser2 = new UserDTO("fuck", "password123");

        UserService userService = new UserService(emptyDAO, validator);

        assertThrows(UserRegistrationValidationException.class, () -> userService.create(testUser2));
    }

    @Test
    void create_invalid_password_length() {
        UserDTO testUser2 = new UserDTO("login", "fuck");

        UserService userService = new UserService(emptyDAO, validator);

        assertThrows(UserRegistrationValidationException.class, () -> userService.create(testUser2));
    }

    @Test
    void create_invalid_password_symbols() {
        UserDTO testUser2 = new UserDTO("login", "fucking_shit");

        UserService userService = new UserService(emptyDAO, validator);

        assertThrows(UserRegistrationValidationException.class, () -> userService.create(testUser2));
    }
}