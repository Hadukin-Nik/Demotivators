package com.demotivators.site.dao;

import com.demotivators.site.dto.UserRegisterDTO;
import com.demotivators.site.models.User;

public interface UserDAO {
    User addUser(UserRegisterDTO userDTO);
}
