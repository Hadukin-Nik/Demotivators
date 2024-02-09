package com.demotivators.site.services;

import com.demotivators.site.dao.UserDAO;
import com.demotivators.site.dto.UserDTO;
import com.demotivators.site.models.User;
import com.demotivators.site.services.exceptions.UserDuplicateException;
import com.demotivators.site.services.exceptions.UserRegistrationValidationException;
import jakarta.validation.*;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDAO userDAO;
    private final Validator validator;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    public User create(UserDTO userDTO) {
        User user = new User(userDTO.getLogin(), userDTO.getPassword());

        var violations = validator.validate(userDTO) ;

        for (var violation : violations) {
            throw new UserRegistrationValidationException(violation.getMessage());
        }

        try{
            user.setId(userDAO.addUser(userDTO));
        } catch (DuplicateKeyException exception) {
            throw new UserDuplicateException();
        }
        return user;
    }

}
