package com.demotivators.site.services;

import com.demotivators.site.dao.UserDAO;
import com.demotivators.site.dto.UserDTO;
import com.demotivators.site.models.User;
import com.demotivators.site.services.exceptions.UserDuplicateException;
import com.demotivators.site.services.exceptions.UserRegistrationValidationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    private final UserDTO testUser1 = new UserDTO("login", "password123");
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    @MockBean
    private UserDAO emptyDAO;

    private UserService userService;
    @BeforeEach
    private void setUp() {
        userService = new UserService(emptyDAO, validator);

        when(emptyDAO.addUser(any(UserDTO.class))).thenReturn(1L);
    }

    @Test
    void create_Happy_Path() {
        User expectedUser = new User(testUser1.getLogin(), testUser1.getPassword());
        expectedUser.setId(1L);

        assertEquals(expectedUser, userService.create(testUser1));
    }

    @Test
    void create_Duplicate_name() {
        when(emptyDAO.addUser(any(UserDTO.class))).thenThrow(new UserDuplicateException());

        assertThrows(UserDuplicateException.class, () -> userService.create(testUser1));
    }

    @Test
    void create_invalid_login() {
        UserDTO testUser2 = new UserDTO("fuck", "password123");

        assertThrows(UserRegistrationValidationException.class, () -> userService.create(testUser2));
    }

    @Test
    void create_invalid_password_length() {
        UserDTO testUser2 = new UserDTO("login", "fuck");

        assertThrows(UserRegistrationValidationException.class, () -> userService.create(testUser2));
    }

    @Test
    void create_invalid_password_symbols() {
        UserDTO testUser2 = new UserDTO("login", "fucking_shit");

        assertThrows(UserRegistrationValidationException.class, () -> userService.create(testUser2));
    }
}