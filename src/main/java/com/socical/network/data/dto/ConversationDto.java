package com.socical.network.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.socical.network.data.entities.Media;
import com.socical.network.data.entities.Message;
import com.socical.network.data.entities.Messenger;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConversationDto {
    private Long id;
    private String name;
    private String color;
    @JsonProperty("isGroup")
    private boolean isGroup;
    private Media image;
    private Messenger user;
    private List<Messenger> guests;
    private Message lastMessage;
}
