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

@Service
@RequiredArgsConstructor
public class MemeService {
    private final FileStorageService fileStorageService;
    private final MemeDAO memeDAO;

    public Meme createMeme(MemeDTO memeDTO, MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        memeDTO.setImage(fileName);
        Meme meme = new Meme(memeDTO.getName(), fileName);

        try{
            meme.setId(memeDAO.addMeme(memeDTO));
        } catch (DuplicateKeyException exception) {
            throw new UserDuplicateException();
        }

        return meme;
    }
}
