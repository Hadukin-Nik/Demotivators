package com.demotivators.site.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownloadsController {
    @GetMapping("/downloads")
    public void showDownloads() {}

    @PostMapping(value = "/downloads", consumes = "application/json")
    public void createDownload(@RequestBody DownloadsController downloadsController) {}
}
