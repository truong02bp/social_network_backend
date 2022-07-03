package com.socical.network.controllers;

import com.socical.network.data.dto.FollowRelationDto;
import com.socical.network.data.entities.User;
import com.socical.network.services.FollowRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/follow-relation")
@RequiredArgsConstructor
public class FollowRelationController {

    private final FollowRelationService followRelationService;

    @GetMapping("/following")
    public ResponseEntity<List<FollowRelationDto>> findFollowingByUserId(@RequestParam("userId") Long userId,
                                                            @RequestParam(value = "username", required = false, defaultValue = "") String username,
                                                            Pageable pageable) {
        List<FollowRelationDto> following = followRelationService.findFollowingByUserIdAndUsername(userId, username, pageable);
        return ResponseEntity.ok(following);
    }

    @GetMapping("/followers")
    public ResponseEntity<List<FollowRelationDto>> findFollowersByUserId(@RequestParam(value = "userId") Long userId,
                                                                         @RequestParam(value = "username", required = false, defaultValue = "") String username,
                                                                         Pageable pageable) {
        List<FollowRelationDto> followers = followRelationService.findFollowersByUserIdAndUsername(userId, username, pageable);
        return ResponseEntity.ok(followers);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteByUserIdAndFollowerId(@RequestParam(value = "userId") Long userId,
                                                              @RequestParam(value = "followerId") Long followerId) {
        followRelationService.deleteByUserIdAndFollowerId(userId, followerId);
        return ResponseEntity.ok("Delete success");
    }
}
