package com.socical.network.data.dto;

import com.socical.network.data.entities.Media;
import com.socical.network.data.entities.Message;
import com.socical.network.data.entities.Messenger;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConversationDto {
    private String name;
    private String color;
    private boolean isGroup;
    private Media image;
    private Messenger user;
    private List<Messenger> guests;
    private Message lastMessage;
}
