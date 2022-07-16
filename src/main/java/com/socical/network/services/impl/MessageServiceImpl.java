package com.socical.network.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.socical.network.data.dto.MessageDto;
import com.socical.network.data.entities.Message;
import com.socical.network.data.entities.Messenger;
import com.socical.network.data.repositories.MessageRepository;
import com.socical.network.data.repositories.MessengerRepository;
import com.socical.network.exceptions.BusinessException;
import com.socical.network.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageServiceImpl implements MessageService {

    @Value("${kafka.message.topic}")
    private String topic;

    private final MessengerRepository messengerRepository;
    private final MessageRepository messageRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper;

    @Override
    public Message create(MessageDto messageDto) {
        Messenger sender = messengerRepository.findById(messageDto.getMessengerId()).orElseThrow(() -> {
                throw BusinessException.builder().message("Messenger not found").status(HttpStatus.NOT_FOUND).build();
            }
        );
        Message message = new Message();
        message.setContent(messageDto.getContent());
        message.setSender(sender);
        message = messageRepository.save(message);
        System.out.println(topic);
        try {
            kafkaTemplate.send(topic, mapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return message;
    }

    @Override
    public List<Message> getMessage(Long conversationId, Pageable pageable) {
        return messageRepository.findAllByChatBoxId(conversationId, pageable);
    }

    @Override
    public List<Message> updateSeen(Long messengerId) {
        return null;
    }

    @Override
    public Message updateReaction(MessageDto messageDto) {
        return null;
    }
}
