package com.demotivators.site.services;

import com.demotivators.site.dao.UserDAO;
import com.demotivators.site.dto.UserDTO;
import com.demotivators.site.models.User;
import com.demotivators.site.services.exceptions.UserDuplicateException;
import com.demotivators.site.services.exceptions.UserRegistrationValidationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    private final UserDTO testUser1 = new UserDTO("login", "password123");
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    @MockBean
    private UserDAO emptyDAO;

    private UserService userService;

    @Test
    void create_Happy_Path() {
        userService = new UserService(emptyDAO, validator);

        when(emptyDAO.addUser(testUser1)).thenReturn(1L);

        User expectedUser = new User(testUser1.getLogin(), testUser1.getPassword());
        expectedUser.setId(1L);

        assertEquals(expectedUser, userService.create(testUser1));
    }

    @Test
    void create_Duplicate_name() {
        userService = new UserService(emptyDAO, validator);

        when(emptyDAO.addUser(testUser1)).thenThrow(new UserDuplicateException());

        assertThrows(UserDuplicateException.class, () -> userService.create(testUser1));
    }

    @Test
    void create_invalid_login() {
        userService = new UserService(emptyDAO, validator);

        UserDTO testUser2 = new UserDTO("fuck", "password123");

        assertThrows(UserRegistrationValidationException.class, () -> userService.create(testUser2));
    }

    @Test
    void create_invalid_password_length() {
        userService = new UserService(emptyDAO, validator);

        UserDTO testUser2 = new UserDTO("login", "fuck");

        assertThrows(UserRegistrationValidationException.class, () -> userService.create(testUser2));
    }

    @Test
    void create_invalid_password_symbols() {
        userService = new UserService(emptyDAO, validator);

        UserDTO testUser2 = new UserDTO("login", "fucking_shit");

        assertThrows(UserRegistrationValidationException.class, () -> userService.create(testUser2));
    }
}