package com.demotivators.site.dao;

import com.demotivators.site.dto.MemeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class MemeDAOImpl implements MemeDAO{
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insert;

    @Autowired
    public MemeDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insert = new SimpleJdbcInsert(jdbcTemplate).withTableName("meme").usingGeneratedKeyColumns("id");
    }

    @Override
    public Long addMeme(MemeDTO memeDTO) {
        var namedParameters = new MapSqlParameterSource()
                .addValue("name", memeDTO.getName())
                .addValue("image", memeDTO.getImage())
                .addValue("creation_date", new Date());


        return (Long) insert.executeAndReturnKey(namedParameters);
    }
}
