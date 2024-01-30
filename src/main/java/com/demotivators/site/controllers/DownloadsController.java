package com.demotivators.site.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/downloads")
public class DownloadsController {
    @GetMapping("/")
    public void showDownloads() {
        System.out.println("showDownloadWorking");
    }

    @PostMapping(value = "/", consumes = "application/json")
    public void createDownload(@RequestBody DownloadsController downloadsController) {}
}
