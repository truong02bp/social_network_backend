package com.socical.network.controllers;

import com.socical.network.data.entities.User;
import com.socical.network.services.FollowRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/follow-relation")
@RequiredArgsConstructor
public class FollowRelationController {

    private final FollowRelationService followRelationService;

    @GetMapping("/following")
    public ResponseEntity<List<User>> findFollowingByUserId(@RequestParam("userId") Long userId) {
        List<User> following = followRelationService.findFollowingByUserId(userId);
        return ResponseEntity.ok(following);
    }

    @GetMapping("/followers")
    public ResponseEntity<List<User>> findFollowersByUserId(@RequestParam("userId") Long userId) {
        List<User> followers = followRelationService.findFollowersByUserId(userId);
        return ResponseEntity.ok(followers);
    }

}
