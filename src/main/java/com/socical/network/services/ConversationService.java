package com.socical.network.services;

import com.socical.network.data.dto.ConversationDto;
import com.socical.network.data.entities.Conversation;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ConversationService {
    Conversation createGroup(List<Long> userIds);

    Conversation create(List<Long> userIds);

    List<ConversationDto> findByUserId(Long userId, Pageable pageable);
}
