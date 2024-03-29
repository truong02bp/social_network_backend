package com.socical.network.controllers;

import com.socical.network.data.dto.MessageDto;
import com.socical.network.data.entities.Message;
import com.socical.network.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
class MessageController {

    private final MessageService messageService;
    private final SimpMessagingTemplate template;


    @GetMapping("/api/v1/message")
    public ResponseEntity<List<Message>> getMessage(@RequestParam("conversationId") Long conversationId,
                                                    Pageable pageable) {
        return ResponseEntity.ok(messageService.getMessage(conversationId, pageable));
    }

    @MessageMapping("/message/send")
    public Message sendMessage(@Payload MessageDto messageDto) {
        Message message = messageService.create(messageDto);
        this.template.convertAndSend("/topic/" + message.getSender().getConversationId(), message);
        return message;
    }

    @MessageMapping("/message/update/seen")
    public List<Message> updateSeen(@Payload MessageDto messageDto) {
        List<Message> messages = messageService.updateSeen(messageDto.getMessengerId());
        this.template.convertAndSend("/topic/update/seen", messages);
        return messages;
    }

    @MessageMapping("/message/update/reaction")
    public Message updateReaction(@Payload MessageDto messageDto) {
        System.out.println(messageDto.getReaction());
        Message message = messageService.updateReaction(messageDto);
        this.template.convertAndSend("/topic/update/reaction", message);
        return message;
    }
}
