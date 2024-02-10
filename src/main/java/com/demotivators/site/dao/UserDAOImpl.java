package com.demotivators.site.dao;

import com.demotivators.site.dto.UserDTO;
import com.demotivators.site.models.User;
import com.demotivators.site.models.UserDataBase;
import com.demotivators.site.services.exceptions.WrongToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class UserDAOImpl implements UserDAO {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insert;

    private final ObjectMapper objectMapper;

    @Autowired
    public UserDAOImpl(JdbcTemplate jdbcTemplate, ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.insert = new SimpleJdbcInsert(jdbcTemplate).withTableName("users").usingGeneratedKeyColumns("id");
        this.objectMapper = objectMapper;
    }

    @Override
    public Long addUser(UserDTO userDTO) {
        var namedParameters = new MapSqlParameterSource()
                .addValue("login", userDTO.getLogin())
                .addValue("password", userDTO.getPassword())
                .addValue("creation_date", new Date())
                .addValue("token", UUID.randomUUID().toString());


        return (Long) insert.executeAndReturnKey(namedParameters);
    }

    @Override
    public User getUserByToken(String token) {
        if(token == null) return null;
        String sql = "SELECT * FROM users WHERE token = ?";

        UserDataBase userDataBase;
        try{
            userDataBase = jdbcTemplate.queryForObject(sql, new Object[]{token}, new BeanPropertyRowMapper<>(UserDataBase.class));
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
        if(userDataBase == null) return null;
        User user = new User(userDataBase);

        return user;
    }
}
