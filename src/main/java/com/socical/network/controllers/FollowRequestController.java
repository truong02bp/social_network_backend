package com.socical.network.controllers;

import com.socical.network.data.entities.FollowRequest;
import com.socical.network.services.FollowRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/follow-request")
@RequiredArgsConstructor
public class FollowRequestController {

    private final FollowRequestService followRequestService;

    @PostMapping
    public ResponseEntity<FollowRequest> create(@RequestParam("userId") Long userId) {
        FollowRequest followRequest = followRequestService.create(userId);
        return ResponseEntity.ok(followRequest);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam("id") Long id) {
        followRequestService.delete(id);
        return ResponseEntity.ok("SUCCESS");
    }

    @PutMapping
    public ResponseEntity<String> acceptFollowRequest(@RequestParam("id") Long id) {
        followRequestService.acceptFollowRequest(id);
        return ResponseEntity.ok("SUCCESS");
    }

}
