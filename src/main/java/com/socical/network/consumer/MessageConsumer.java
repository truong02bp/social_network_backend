package com.socical.network.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.socical.network.data.entities.Message;
import com.socical.network.data.repositories.ConversationRepository;
import com.socical.network.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class MessageConsumer {

    private final ObjectMapper mapper;
    private final ConversationRepository conversationRepository;

    @KafkaListener(topics = "${kafka.message.topic}", groupId = "${kafka.group.id}")
    public void listenMessage(String json) {
        System.out.println("Received Message in group: " + json);
        Message message;
        try {
            message = mapper.readValue(json, Message.class);
        } catch (JsonProcessingException e) {
            throw BusinessException.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(e.getMessage()).build();
        }
        Long conversationId = message.getSender().getConversationId();
        int rowUpdated = conversationRepository.rowUpdate(conversationId, message.getId());
        if (rowUpdated != 1) {
            throw BusinessException.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message("Can't update last message with conversation id " + conversationId).build();
        }
        // send logic notification here
    }
}
