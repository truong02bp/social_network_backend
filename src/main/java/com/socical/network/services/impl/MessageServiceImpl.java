package com.socical.network.services.impl;

import com.socical.network.data.dto.MessageDto;
import com.socical.network.data.entities.Message;
import com.socical.network.data.entities.Messenger;
import com.socical.network.data.repositories.MessageRepository;
import com.socical.network.data.repositories.MessengerRepository;
import com.socical.network.exceptions.BusinessException;
import com.socical.network.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessengerRepository messengerRepository;
    private final MessageRepository messageRepository;

    @Override
    @Transactional
    public Message create(MessageDto messageDto) {
        Messenger sender = messengerRepository.findById(messageDto.getMessengerId()).orElseThrow(() -> {
                throw BusinessException.builder().message("Messenger not found").status(HttpStatus.NOT_FOUND).build();
            }
        );
        Message message = new Message();
        message.setContent(messageDto.getContent());
        message.setSender(sender);
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getMessage(Long conversationId, Pageable pageable) {
        return messageRepository.findAllByChatBoxId(conversationId, pageable);
    }
}
