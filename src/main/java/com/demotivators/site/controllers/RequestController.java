package com.demotivators.site.controllers;

import com.demotivators.site.dto.RequestDTO;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/requests")
public class RequestController {
    @GetMapping
    public void showRequests() {}

    @GetMapping("/friends")
    public void showFriends() {}

    @PutMapping(value = "/{id}", consumes = "application/json")
    public void updateRequest(@PathVariable int requestID) {}


}
