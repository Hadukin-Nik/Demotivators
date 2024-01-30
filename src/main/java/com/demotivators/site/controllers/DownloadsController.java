package com.demotivators.site.controllers;

import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/downloads")
public class DownloadsController {
    @GetMapping
    public void showDownloads() {
        System.out.println("showDownloadWorking");
    }

    @PostMapping(consumes = "application/json")
    public void createDownload(@RequestBody DownloadsController downloadsController) {}
}
