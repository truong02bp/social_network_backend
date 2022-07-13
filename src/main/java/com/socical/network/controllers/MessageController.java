package com.socical.network.controllers;

import com.socical.network.data.dto.MessageDto;
import com.socical.network.data.entities.Message;
import com.socical.network.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
class MessageController {

    private final MessageService messageService;
    private final SimpMessagingTemplate template;

    @MessageMapping("/message/send")
    public Message sendMessage(@Payload MessageDto messageDto) {
        Message message = messageService.create(messageDto);
        this.template.convertAndSend("/topic/" + message.getSender().getConversationId(), message);
        return message;
    }

}
