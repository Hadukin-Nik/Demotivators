package com.demotivators.site.controllers;

import com.demotivators.site.configuration.MemeScrollProperties;
import com.demotivators.site.dto.CommentDTO;
import com.demotivators.site.dto.MemeDTO;
import com.demotivators.site.models.Meme;
import com.demotivators.site.services.MemeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController()
@RequestMapping("/memes")
@RequiredArgsConstructor
public class MemesController {

    private final MemeService memeService;

    private final ObjectMapper objectMapper;
    private final MemeScrollProperties memeScrollProperties;

    @GetMapping
    public List<Meme> showMemesScroll() {
        return memeService.getList();
    }

    @PostMapping(value = "/upload")
    public MemeDTO uploadMeme(@RequestParam("model") String memeDTO, @RequestParam("file") MultipartFile file) {
        MemeDTO response = null;

        try {
            response = objectMapper.readValue(memeDTO, MemeDTO.class);

            memeService.create(response, file);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return response;
    }

    @GetMapping(value = "/{id}")
    public void showMeme(@PathVariable int memeId) {

    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public void redactMeme(@PathVariable int memeId) {

    }

    @PutMapping(value = "/{id}/comments", consumes = "application/json")
    public void commentMeme(@PathVariable int memeId, @RequestBody CommentDTO commentDTO) {

    }

    @DeleteMapping(value = "/{id}", consumes = "application/json")
    public void deleteMeme(@PathVariable int memeId) {

    }
}
