package com.socical.network.controllers;

import com.socical.network.data.entities.FollowRequest;
import com.socical.network.services.FollowRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/follow-request")
@RequiredArgsConstructor
public class FollowRequestController {

    private final FollowRequestService followRequestService;

    @GetMapping
    public ResponseEntity<List<FollowRequest>> getByUserId(@RequestParam("userId") Long userId){
        return ResponseEntity.ok(followRequestService.getByUserId(userId));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countByUserId(@RequestParam("userId") Long userId){
        return ResponseEntity.ok(followRequestService.countByUserId(userId));
    }

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
