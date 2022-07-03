package com.socical.network.services;

import com.socical.network.data.dto.FollowRelationDto;
import com.socical.network.data.entities.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FollowRelationService {
    List<FollowRelationDto> findFollowingByUserIdAndUsername(Long userId, String username, Pageable pageable);
    List<FollowRelationDto> findFollowersByUserIdAndUsername(Long userId, String username, Pageable pageable);
    void deleteByUserIdAndFollowerId(Long userId, Long followerId);
}
