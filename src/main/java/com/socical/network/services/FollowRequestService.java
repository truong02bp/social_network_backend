package com.socical.network.services;

import com.socical.network.data.entities.FollowRequest;

import java.util.List;

public interface FollowRequestService {

    List<FollowRequest> getByUserId(Long userId);
    int countByUserId(Long userId);
    FollowRequest create(Long receiverId);
    void delete(Long id);
    void acceptFollowRequest(Long id);
}
