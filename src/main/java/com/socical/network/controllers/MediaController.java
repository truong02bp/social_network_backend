package com.socical.network.controllers;

import com.socical.network.data.dto.MediaDto;
import com.socical.network.data.entities.Media;
import com.socical.network.services.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MediaController {
    private final MediaService mediaService;

    @PostMapping("/media")
    public ResponseEntity<Media> create(@RequestBody MediaDto mediaDto) {
        Media media = mediaService.create(mediaDto);
        return ResponseEntity.ok(media);
    }
}
