package com.socical.network.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageDto {
    private Long chatBoxId;
    private Long messengerId;
    private Long messageId;
    private Long mediaId;
    private boolean isMedia;
    private String reaction;
    private byte[] bytes;
    private String content;
    private String name;
}
