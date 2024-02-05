package com.demotivators.site.services;

import com.demotivators.site.dao.UserDAO;
import com.demotivators.site.dto.UserRegisterDTO;
import com.demotivators.site.models.User;
import com.demotivators.site.services.exceptions.UserDuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDAO userDAO;

    public User createUser(UserRegisterDTO userDTO) {
        User user = new User(userDTO.getLogin(), userDTO.getPassword());

        try{
            user.setId(userDAO.addUser(userDTO));
        } catch (DuplicateKeyException exception) {
            throw new UserDuplicateException();
        }
        return user;
    }

}
