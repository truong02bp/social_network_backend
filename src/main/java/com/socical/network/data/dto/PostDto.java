package com.socical.network.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostDto {
    private String caption;
    private Long userId;
    private List<Long> tags;
    private List<MediaDto> medias;
}
