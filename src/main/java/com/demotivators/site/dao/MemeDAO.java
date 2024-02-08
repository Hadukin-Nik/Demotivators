package com.demotivators.site.dao;


import com.demotivators.site.dto.MemeDTO;
import com.demotivators.site.models.Meme;

import java.util.List;

public interface MemeDAO {
    Long addMeme(MemeDTO memeDTO);

    List<Meme> getList();
}
