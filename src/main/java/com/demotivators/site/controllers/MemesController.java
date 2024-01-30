package com.demotivators.site.controllers;

import com.demotivators.site.dto.MemeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemesController {

    @GetMapping("/scroll")
    public void showMemesScroll() {

    }
    @GetMapping(value = "/memeUpload")
    public void showUploadMemePage() {

    }

    @PostMapping(value = "/memeUpload", consumes =  "application/json")
    public void uploadMeme(@RequestBody MemeDTO memeDTO) {

    }

    @GetMapping(value = "/memeRedact")
    public void showRedactMemePage() {

    }

    @PostMapping(value = "/memeRedact", consumes = "application/json")
    public void redactMeme(@RequestBody MemeDTO memeDTO) {

    }
}
