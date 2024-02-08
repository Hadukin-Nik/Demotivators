package com.demotivators.site.services;

import com.demotivators.site.dao.MemeDAO;
import com.demotivators.site.dto.MemeDTO;
import com.demotivators.site.models.Meme;
import com.demotivators.site.services.exceptions.UserDuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemeService {
    private final FileStorageService fileStorageService;
    private final MemeDAO memeDAO;

    public Meme createMeme(MemeDTO memeDTO, MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        memeDTO.setImage(fileName);

        Meme meme = new Meme(memeDTO.getName(), fileName);

        meme.setId(memeDAO.addMeme(memeDTO));

        return meme;
    }

    public List<Meme> getMemeList() {
        return memeDAO.getList();
    }
}
