package com.demotivators.site.services;

import com.demotivators.site.dao.MemeDAO;
import com.demotivators.site.dao.UserDAO;
import com.demotivators.site.dto.MemeDTO;
import com.demotivators.site.dto.UserDTO;
import com.demotivators.site.models.Meme;
import com.demotivators.site.models.User;
import com.demotivators.site.services.exceptions.WrongImageExtensionException;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

            @Override
            public List<Meme> getList() {
                return new ArrayList<>();
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

    @Test
    void createMeme_Wrong_Image_Extension() {
        MultipartFile multipartFile = new MockMultipartFile("file",new byte[]{});
        MemeDTO memeDTO = new MemeDTO("base_name", "base_photo");
        MemeDAO memeDAO = new MemeDAO() {
            @Override
            public Long addMeme(MemeDTO memeDTO) {
                return 1L;
            }

            @Override
            public List<Meme> getList() {
                return new ArrayList<>();
            }
        };


        FileStorageService fileStorageService = new FileStorageService() {
            @Override
            public String storeFile(MultipartFile file) {
                throw new WrongImageExtensionException();
            }
        };

        MemeService memeService = new MemeService(fileStorageService, memeDAO);

        assertThrows(WrongImageExtensionException.class, () -> memeService.createMeme(memeDTO, multipartFile));
    }

    @Test
    void get_Meme_List() {
        List<Meme> expectedMemes = List.of(new Meme("122", "123"), new Meme("124", "125"));

        MultipartFile multipartFile = new MockMultipartFile("file",new byte[]{});
        MemeDAO memeDAO = new MemeDAO() {
            @Override
            public Long addMeme(MemeDTO memeDTO) {
                return null;
            }

            @Override
            public List<Meme> getList() {
                return expectedMemes;
            }
        };

        FileStorageService fileStorageService = new FileStorageService() {
            @Override
            public String storeFile(MultipartFile file) {
                return null;
            }
        };

        MemeService memeService = new MemeService(fileStorageService, memeDAO);

        assertEquals(expectedMemes, memeService.getMemeList());
    }
}