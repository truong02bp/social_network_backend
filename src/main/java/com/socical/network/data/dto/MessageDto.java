package com.socical.network.data.dto;

import com.socical.network.common.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageDto {
    private Long messengerId;
    private MessageType type;
    private byte[] bytes;
    private String content;
}
