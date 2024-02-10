package com.demotivators.site.dao;

import com.demotivators.site.dto.UserDTO;
import com.demotivators.site.models.User;

public interface UserDAO {
    Long addUser(UserDTO userDTO);

    User getUserByToken(String token);
}
