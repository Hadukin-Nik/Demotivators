package com.demotivators.site.controllers;

import com.demotivators.site.dto.CommentDTO;
import com.demotivators.site.dto.MemeDTO;
import com.demotivators.site.services.FileStorageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController()
@RequestMapping("/memes")
public class MemesController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public void showMemesScroll() {

    }

    @PostMapping(value = "/upload")
    public MemeDTO uploadMeme(@RequestParam("model") String memeDTO, @RequestParam("file") MultipartFile file) {
        MemeDTO response = null;

        try {
            String fileName = fileStorageService.storeFile(file);

            ServletUriComponentsBuilder.fromCurrentContextPath().path(fileName).toUriString();

            response = objectMapper.readValue(memeDTO, MemeDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        //response.setImage(file.getOriginalFilename());
        return response;
    }

    @GetMapping(value = "/{id}")
    public void showMeme(@PathVariable int memeId) {

    }

    @PatchMapping(value = "/{id}", consumes = "application/json")
    public void redactMeme(@PathVariable int memeId) {

    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public void commentMeme(@PathVariable int memeId, @RequestBody CommentDTO commentDTO) {

    }

    @DeleteMapping(value = "/{id}", consumes = "application/json")
    public void deleteMeme(@PathVariable int memeId) {

    }
}
