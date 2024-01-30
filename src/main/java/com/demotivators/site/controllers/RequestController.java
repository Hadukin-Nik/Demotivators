package com.demotivators.site.controllers;

import com.demotivators.site.dto.RequestDTO;
import org.springframework.web.bind.annotation.*;

@RestController("/requests")
public class RequestController {
    @GetMapping("/")
    public void showRequests() {}

    @GetMapping("/friends")
    public void showFriends() {}

    @DeleteMapping(value = "/{id}", consumes = "application/json")
    public void deleteRequest(@PathVariable int requestID) {}

    @PatchMapping(value = "/{id}", consumes = "application/json")
    public void approveRequest(@PathVariable int requestID) {}


}
