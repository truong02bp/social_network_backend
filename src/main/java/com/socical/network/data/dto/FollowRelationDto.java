package com.socical.network.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.socical.network.data.entities.User;
import lombok.Data;

@Data
public class FollowRelationDto {
    private User user;
    @JsonProperty("isFollowing")
    private boolean isFollowing;
}
