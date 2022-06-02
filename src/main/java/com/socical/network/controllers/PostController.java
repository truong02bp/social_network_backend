package com.socical.network.controllers;

import com.socical.network.data.dto.PostDto;
import com.socical.network.data.entities.Post;
import com.socical.network.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/user")
    public ResponseEntity<List<Post>> getByUserId(@RequestParam("userId") Long userId) {
        List<Post> posts = postService.findAllByUserId(userId);
        return ResponseEntity.ok(posts);
    }

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody PostDto postDto) {
        Post post = postService.create(postDto);
        return ResponseEntity.ok(post);
    }

}
