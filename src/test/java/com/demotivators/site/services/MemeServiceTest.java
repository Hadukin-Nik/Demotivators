package com.demotivators.site.services;

import com.demotivators.site.dao.MemeDAO;
import com.demotivators.site.dto.MemeDTO;
import com.demotivators.site.models.Meme;
import com.demotivators.site.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemeServiceTest {
    @Test
    void createMeme_Happy_Path() {
        MultipartFile multipartFile = new MockMultipartFile("file",new byte[]{});
        MemeDTO memeDTO = new MemeDTO("base_name", "base_photo");
        MemeDAO memeDAO = new MemeDAO() {
            @Override
            public Long addMeme(MemeDTO memeDTO) {
                return 1L;
            }
        };


        FileStorageService fileStorageService = new FileStorageService() {
            @Override
            public String storeFile(MultipartFile file) {
                return memeDTO.getImage();
            }
        };

        MemeService memeService = new MemeService(fileStorageService, memeDAO);

        Meme expectedMeme = new Meme(memeDTO.getName(), memeDTO.getImage());
        expectedMeme.setId(1L);

        assertEquals(expectedMeme, memeService.createMeme(memeDTO, multipartFile));
    }
}