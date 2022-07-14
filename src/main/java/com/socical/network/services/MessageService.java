package com.socical.network.services;

import com.socical.network.data.dto.MessageDto;
import com.socical.network.data.entities.Message;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MessageService {
    Message create(MessageDto messageDto);
    List<Message> getMessage(Long conversationId, Pageable pageable);
}
