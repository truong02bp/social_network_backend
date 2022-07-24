package com.socical.network.controllers;

import com.socical.network.data.dto.MediaDto;
import com.socical.network.data.entities.MessageMedia;
import com.socical.network.services.MessageMediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/message/media")
@RequiredArgsConstructor
public class MessageMediaController {

    private final MessageMediaService messageMediaService;

    @PostMapping
    public ResponseEntity<List<MessageMedia>> createAll(@RequestBody List<MediaDto> mediaList) {
        return ResponseEntity.ok(messageMediaService.createAll(mediaList));
    }
}
