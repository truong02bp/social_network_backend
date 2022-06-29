package com.socical.network.data.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String oldPassword;
    private String newPassword;
}
