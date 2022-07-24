package com.socical.network.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.socical.network.data.dto.MessageDto;
import com.socical.network.data.entities.*;
import com.socical.network.data.repositories.MessageMediaRepository;
import com.socical.network.data.repositories.MessageRepository;
import com.socical.network.data.repositories.MessengerRepository;
import com.socical.network.data.repositories.ReactionRepository;
import com.socical.network.exceptions.BusinessException;
import com.socical.network.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageServiceImpl implements MessageService {

    @Value("${kafka.message.topic}")
    private String topic;

    private final MessengerRepository messengerRepository;
    private final MessageRepository messageRepository;
    private final ReactionRepository reactionRepository;
    private final MessageMediaRepository messageMediaRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper;

    @Override
    public Message create(MessageDto messageDto) {
        Messenger sender = messengerRepository.findById(messageDto.getMessengerId()).orElseThrow(() -> {
                throw BusinessException.builder().message("Messenger not found").status(HttpStatus.NOT_FOUND).build();
            }
        );
        Message message = new Message();
        switch (messageDto.getType()) {
            case TEXT -> message.setContent(messageDto.getContent());
            case IMAGE -> {
                if (messageDto.getMessageMediaIds() != null) {
                    message.setMedias(new HashSet<>(messageMediaRepository.findAllById(messageDto.getMessageMediaIds())));
                }
            }
            case VIDEO -> {

            }
        }
        message.setType(messageDto.getType());
        message.setSender(sender);
        List<MessageInteraction> interactions = new ArrayList<>();
        MessageInteraction interaction = new MessageInteraction();
        interaction.setSeenBy(sender);
        interactions.add(interaction);
        message.setInteractions(interactions);
        message = messageRepository.save(message);
        try {
            kafkaTemplate.send(topic, mapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return message;
    }

    @Override
    public List<Message> getMessage(Long conversationId, Pageable pageable) {
        return messageRepository.findAllByConversationId(conversationId, pageable);
    }

    @Override
    public List<Message> updateSeen(Long messengerId) {
        List<Message> messages = new ArrayList<>();
        Messenger messenger = messengerRepository.findById(messengerId).orElseThrow(() -> {
                throw BusinessException.builder().message("Messenger not found").status(HttpStatus.NOT_FOUND).build();
            }
        );
        List<Message> needUpdateMessages = new ArrayList<>();
        Long lastMessageId = messageRepository.findLastMessageId(messengerId);
        if (lastMessageId == null) {
            needUpdateMessages.addAll(messageRepository.findAllByConversationId(messenger.getConversationId()));
        } else {
            needUpdateMessages.addAll(messageRepository.findAllByConversationIdMoreThanMessageId(messenger.getConversationId(), messengerId));
        }
        needUpdateMessages.forEach(message -> {
            List<MessageInteraction> interactions = message.getInteractions().stream()
                .filter(messageDetail -> messageDetail.getSeenBy().getId().equals(messengerId)).toList();
            if (interactions.isEmpty()) {
                MessageInteraction interaction = new MessageInteraction();
                interaction.setSeenBy(messenger);
                message.getInteractions().add(interaction);
                messages.add(messageRepository.save(message));
            }
        });
        return messages;
    }

    @Override
    public Message updateReaction(MessageDto messageDto) {
        Messenger reactBy = messengerRepository.findById(messageDto.getMessengerId()).orElseThrow(() -> {
                throw BusinessException.builder().message("Messenger not found").status(HttpStatus.NOT_FOUND).build();
            }
        );
        Message message = messageRepository.findById(messageDto.getMessageId()).orElseThrow(() -> {
            throw BusinessException.builder().message("Message not found").status(HttpStatus.NOT_FOUND).build();
        });
        if (StringUtils.isBlank(messageDto.getReaction())) {
            throw BusinessException.builder().message("Reaction value is invalid").status(HttpStatus.BAD_REQUEST).build();
        }
        Reaction reaction = reactionRepository.findByCode(messageDto.getReaction());
        for (MessageInteraction interaction : message.getInteractions()) {
            if (Objects.equals(interaction.getSeenBy().getId(), reactBy.getId())) {
                if (interaction.getReaction() != null && interaction.getReaction().getId().equals(reaction.getId()))
                    interaction.setReaction(null);
                else
                    interaction.setReaction(reaction);
            }
        }
        return messageRepository.save(message);
    }
}
