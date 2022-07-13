package com.socical.network.controllers;

import com.socical.network.data.dto.ConversationDto;
import com.socical.network.data.entities.Conversation;
import com.socical.network.services.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/conversation")
@RequiredArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;

    @GetMapping
    public ResponseEntity<List<ConversationDto>> findByUserId(@RequestParam("userId") Long userId, Pageable pageable) {
        return ResponseEntity.ok(conversationService.findByUserId(userId, pageable));
    }

    @PostMapping
    public ResponseEntity<Conversation> createConversation(@RequestParam("userIds") List<Long> userIds) {
        Conversation conversation = conversationService.create(userIds);
        return ResponseEntity.ok(conversation);
    }

    @PostMapping("/group")
    public ResponseEntity<Conversation> createGroup(@RequestParam("userIds") List<Long> userIds) {
        Conversation conversation = conversationService.createGroup(userIds);
        return ResponseEntity.ok(conversation);
    }
}
