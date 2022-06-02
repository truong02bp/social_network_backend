package com.socical.network.services;

import com.socical.network.data.entities.User;

import java.util.List;

public interface FollowRelationService {
    List<User> findFollowingByUserId(Long userId);
    List<User> findFollowersByUserId(Long userId);
}
