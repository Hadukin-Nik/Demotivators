package com.demotivators.site.services;

import com.demotivators.site.dao.UserDAO;
import com.demotivators.site.dto.UserDTO;
import com.demotivators.site.models.User;
import com.demotivators.site.services.exceptions.UserDuplicateException;
import com.demotivators.site.services.exceptions.UserRegistrationValidationException;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDAO userDAO;
    private final Validator validator;

    public UserService(UserDAO userDAO, Validator validator) {
        this.userDAO = userDAO;
        this.validator = validator;
    }

    public User create(UserDTO userDTO) {
        User user = new User(userDTO.getLogin(), userDTO.getPassword());

        var violations = validator.validate(userDTO) ;

        StringBuilder stringBuilderForException = new StringBuilder();
        for (var violation : violations) {
            stringBuilderForException.append(violation.getMessage() + "\n");
        }
        if(!stringBuilderForException.equals("")) {
            throw new UserRegistrationValidationException(stringBuilderForException.toString());
        }


        try{
            user.setId(userDAO.addUser(userDTO));
        } catch (DuplicateKeyException exception) {
            throw new UserDuplicateException();
        }
        return user;
    }

}
