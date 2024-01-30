package com.demotivators.site.controllers;

import com.demotivators.site.dto.RequestDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
    @GetMapping("/requests")
    public void showRequests() {}

    @GetMapping("/friends")
    public void showFriends() {}

    @PostMapping(value = "/requests/*/rejected", consumes = "application/json")
    public void deleteRequest(@RequestBody RequestDTO requestDTO) {}

    @PostMapping(value = "/requests/*/approved", consumes = "application/json")
    public void approveRequest(@RequestBody RequestDTO requestDTO) {}


}
