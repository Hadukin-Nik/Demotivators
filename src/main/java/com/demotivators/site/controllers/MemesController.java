package com.demotivators.site.controllers;

import com.demotivators.site.dto.CommentDTO;
import com.demotivators.site.dto.MemeDTO;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/memes")
public class MemesController {

    @GetMapping()
    public void showMemesScroll() {

    }

    @PostMapping(value = "/upload", consumes =  "application/json")
    public void uploadMeme(@RequestBody MemeDTO memeDTO) {

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
