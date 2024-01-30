package com.demotivators.site.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/collections")
public class CollectionsController {

    @GetMapping
    public void showCollections() {}

    @PostMapping(consumes = "application/json")
    public void createCollection() {}
}