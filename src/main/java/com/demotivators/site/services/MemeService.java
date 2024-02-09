package com.demotivators.site.services;

import com.demotivators.site.dao.MemeDAO;
import com.demotivators.site.dto.MemeDTO;
import com.demotivators.site.models.Meme;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemeService {
    private final FileStorageService fileStorageService;
    private final MemeDAO memeDAO;

    public Meme create(MemeDTO memeDTO, MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        memeDTO.setImage(fileName);

        Meme meme = new Meme(memeDTO.getName(), fileName);

        meme.setId(memeDAO.addMeme(memeDTO));

        return meme;
    }

    public List<Meme> getList() {
        return memeDAO.getList();
    }
}
