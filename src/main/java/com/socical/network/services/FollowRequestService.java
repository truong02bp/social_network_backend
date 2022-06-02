package com.socical.network.services;

import com.socical.network.data.entities.FollowRequest;

public interface FollowRequestService {

    FollowRequest create(Long receiverId);
    void delete(Long id);
    void acceptFollowRequest(Long id);
}
