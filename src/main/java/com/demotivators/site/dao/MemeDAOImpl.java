package com.demotivators.site.dao;

import com.demotivators.site.configuration.MemeScrollProperties;
import com.demotivators.site.dto.MemeDTO;
import com.demotivators.site.models.Meme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class MemeDAOImpl implements MemeDAO{
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insert;
    private final Long countOfMemeInScroll;

    @Autowired
    public MemeDAOImpl(JdbcTemplate jdbcTemplate, MemeScrollProperties memeScrollProperties) {
        this.jdbcTemplate = jdbcTemplate;
        this.insert = new SimpleJdbcInsert(jdbcTemplate).withTableName("meme").usingGeneratedKeyColumns("id");

        countOfMemeInScroll = memeScrollProperties.getMax_count_of_memes();
    }

    @Override
    public Long addMeme(MemeDTO memeDTO) {
        var namedParameters = new MapSqlParameterSource()
                .addValue("name", memeDTO.getName())
                .addValue("image", memeDTO.getImage())
                .addValue("creation_date", new Date());


        return (Long) insert.executeAndReturnKey(namedParameters);
    }

    @Override
    public List<Meme> getList() {
        String sql = "Select * from public.meme limit " + countOfMemeInScroll;
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

        List<Meme> memeList = new ArrayList<>();

        for (Map<String, Object> map : list)
        {
            Meme meme = new Meme((String) map.get("name"), (String) map.get("image"));
            meme.setId((Long) map.get("id"));
            meme.setCreationDate((Date) map.get("creationDate"));

            memeList.add(meme);
        }
        return memeList;
    }
}
