package com.socical.network.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class ProfileDto {
    private int posts;
    private int followers;
    private int following;
}
