package com.demotivators.site.dao;

import com.demotivators.site.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class UserDAOImpl implements UserDAO {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insert;

    @Autowired
    public UserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insert = new SimpleJdbcInsert(jdbcTemplate).withTableName("users").usingGeneratedKeyColumns("id");
    }

    @Override
    public Long addUser(UserDTO userDTO) {
        var namedParameters = new MapSqlParameterSource()
                .addValue("login", userDTO.getLogin())
                .addValue("password", userDTO.getPassword())
                .addValue("creation_date", new Date());


        return (Long) insert.executeAndReturnKey(namedParameters);
    }
}
