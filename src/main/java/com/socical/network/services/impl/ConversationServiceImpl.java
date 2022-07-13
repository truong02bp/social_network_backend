package com.socical.network.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socical.network.data.dto.ConversationDto;
import com.socical.network.data.entities.Conversation;
import com.socical.network.data.entities.Messenger;
import com.socical.network.data.entities.User;
import com.socical.network.data.repositories.ConversationRepository;
import com.socical.network.data.repositories.MessageRepository;
import com.socical.network.data.repositories.UserRepository;
import com.socical.network.exceptions.BusinessException;
import com.socical.network.services.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;
    private final ObjectMapper mapper;
    private final MessageRepository messageRepository;

    @Override
    public Conversation createGroup(List<Long> userIds) {
        if (userIds.isEmpty()) {
            throw BusinessException.builder().message("Conversation member must not be empty").status(HttpStatus.BAD_REQUEST).build();
        }
        Conversation conversation = new Conversation();
        conversationRepository.save(conversation);
        StringBuilder name = new StringBuilder();
        List<Messenger> messengers = userRepository.findAllById(userIds)
            .stream().map(user -> {
                name.append(user.getName().substring(user.getName().lastIndexOf(" ") + 1)).append(", ");
                Messenger messenger = new Messenger();
                messenger.setUser(user);
                messenger.setConversationId(conversation.getId());
                return messenger;
            }).toList();
        conversation.setIsGroup(true);
        conversation.setName(name.substring(0, name.length() - 2));
        conversation.setMessengers(messengers);
        return conversationRepository.save(conversation);
    }

    @Override
    public Conversation create(List<Long> userIds) {
        if (userIds.isEmpty()) {
            throw BusinessException.builder().message("Conversation member must not be empty").status(HttpStatus.BAD_REQUEST).build();
        }
        Conversation conversation = new Conversation();
        conversationRepository.save(conversation);
        List<User> users = userRepository.findAllById(userIds);
        if (userIds.size() != users.size()) {
            throw BusinessException.builder().message("Contains invalid user id").status(HttpStatus.BAD_REQUEST).build();
        }
        List<Messenger> messengers = users.stream().map(user -> {
            Messenger messenger = new Messenger();
            messenger.setUser(user);
            messenger.setConversationId(conversation.getId());
            return messenger;
        }).collect(Collectors.toList());
        conversation.setMessengers(messengers);
        return conversationRepository.save(conversation);
    }

    @Override
    public List<ConversationDto> findByUserId(Long userId, Pageable pageable) {
        return conversationRepository.findByUserId(userId, pageable).stream().map(conversation -> {
            ConversationDto dto = mapper.convertValue(conversation, ConversationDto.class);
            List<Messenger> guests = new ArrayList<>();
            conversation.getMessengers().forEach(messenger -> {
                if (messenger.getUser().getId().equals(userId)) {
                    dto.setUser(messenger);
                } else {
                    guests.add(messenger);
                }
            });
//            dto.setLastMessage(messageRepository.findLastMessageConversation(conversation.getId()));
            dto.setGuests(guests);
            return dto;
        }).collect(Collectors.toList());
    }

}
