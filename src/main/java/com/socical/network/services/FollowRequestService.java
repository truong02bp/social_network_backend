package com.socical.network.services;

import com.socical.network.data.entities.FollowRequest;

public interface FollowRequestService {

    FollowRequest create(Long receiverId);

}
