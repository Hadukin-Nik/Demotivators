package com.demotivators.site.dao;

import com.demotivators.site.dto.UserRegisterDTO;
import com.demotivators.site.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDAOImpl implements UserDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User addUser(UserRegisterDTO userDTO) {
        User user = new User();

        jdbcTemplate.update("Insert into users (login, password) values(?, ?)", userDTO.getLogin(), userDTO.getPassword());

        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());

        return user;
    }
}
