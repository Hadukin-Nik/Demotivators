package com.demotivators.site.services;

import com.demotivators.site.dao.UserDAO;
import com.demotivators.site.dto.UserRegisterDTO;
import com.demotivators.site.models.User;
import com.demotivators.site.services.exceptions.UserDuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User createUser(UserRegisterDTO userDTO) {
        User user = new User();
        user.setPassword(userDTO.getPassword());
        user.setLogin(userDTO.getLogin());

        try{
            user.setId(userDAO.addUser(userDTO));
        } catch (DuplicateKeyException exception) {
            throw new UserDuplicateException();
        }
        return user;
    }

}
