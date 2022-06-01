package com.socical.network.controllers;

import com.socical.network.data.entities.FollowRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/follow-request")
public class FollowRequestController {

    @PostMapping
    public ResponseEntity<FollowRequest> create(@RequestParam("userId") Long userId) {

    }

}
