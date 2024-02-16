package com.demotivators.site.services;

import com.demotivators.site.dao.MemeDAO;
import com.demotivators.site.dto.MemeDTO;
import com.demotivators.site.models.Meme;
import com.demotivators.site.services.exceptions.WrongImageExtensionException;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(SpringExtension.class)
class MemeServiceTest {

    private final MemeDTO memeDTO = new MemeDTO("base_name", "base_photo");
    private final MultipartFile multipartFile = new MockMultipartFile("file",new byte[]{});

    @MockBean
    private FileStorageService fileStorageService;

    @MockBean
    private MemeDAO memeDAO;

    private MemeService memeService;

    @BeforeEach
    private void setUp() {
         memeService = new MemeService(fileStorageService, memeDAO);

         when(memeDAO.addMeme(memeDTO)).thenReturn(1L);
    }

    @Test
    void create_Happy_Path() {
        when(fileStorageService.storeFile(multipartFile)).thenReturn(memeDTO.getImage());

        Meme expectedMeme = new Meme(memeDTO.getName(), memeDTO.getImage());
        expectedMeme.setId(1L);

        assertEquals(expectedMeme, memeService.create(memeDTO, multipartFile));
    }

    @Test
    void create_Wrong_Image_Extension() {
        when(fileStorageService.storeFile(multipartFile)).thenThrow(new WrongImageExtensionException());

        assertThrows(WrongImageExtensionException.class, () -> memeService.create(memeDTO, multipartFile));
    }

    @Test
    void getList_Happy_test() {
        List<Meme> expectedMemes = List.of(new Meme("122", "123"), new Meme("124", "125"));

        when(memeDAO.getList()).thenReturn(expectedMemes);
        when(fileStorageService.storeFile(multipartFile)).thenReturn(null);

        assertEquals(expectedMemes, memeService.getList());
    }
}