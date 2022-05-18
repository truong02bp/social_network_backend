package com.socical.network.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MediaDto {
    private String name;
    private String contentType;
    private byte[] bytes;
}
