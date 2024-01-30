package com.demotivators.site.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollectionsController {

    @GetMapping("/collections")
    public void showCollections() {}

    @PostMapping(value = "/collections/create", consumes = "application/json")
    public void createCollection() {}
}
