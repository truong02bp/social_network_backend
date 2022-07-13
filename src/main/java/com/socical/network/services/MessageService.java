package com.socical.network.services;

import com.socical.network.data.dto.MessageDto;
import com.socical.network.data.entities.Message;

public interface MessageService {
    Message create(MessageDto messageDto);
}
