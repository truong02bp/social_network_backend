package com.socical.network.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.socical.network.common.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDto {
    private Long messageId;
    private Long messengerId;
    private String reaction;
    private MessageType type;
    private String content;
    private List<Long> messageMediaIds;
}
