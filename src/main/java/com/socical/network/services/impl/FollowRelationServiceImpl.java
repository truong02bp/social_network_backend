package com.socical.network.services.impl;

import com.socical.network.data.dto.FollowRelationDto;
import com.socical.network.data.entities.FollowRelation;
import com.socical.network.data.entities.User;
import com.socical.network.data.repositories.FollowRelationRepository;
import com.socical.network.exceptions.BusinessException;
import com.socical.network.services.FollowRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowRelationServiceImpl implements FollowRelationService {

    private final FollowRelationRepository followRelationRepository;

    @Override
    public List<FollowRelationDto> findFollowingByUserIdAndUsername(Long userId, String username, Pageable pageable) {
        return followRelationRepository.findFollowingByUserId(userId, username, pageable).stream().map(user -> {
            FollowRelationDto followRelation = new FollowRelationDto();
            followRelation.setUser(user);
            followRelation.setFollowing(true);
            return followRelation;
        }).collect(Collectors.toList());
    }

    @Override
    public List<FollowRelationDto> findFollowersByUserIdAndUsername(Long userId, String username, Pageable pageable) {
        return followRelationRepository.findFollowersByUserId(userId, username, pageable).stream().map(user -> {
            int isFollowing = followRelationRepository.checkFollowing(userId, user.getId());
            FollowRelationDto followRelation = new FollowRelationDto();
            followRelation.setUser(user);
            followRelation.setFollowing(isFollowing == 1);
            return followRelation;
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteByUserIdAndFollowerId(Long userId, Long followerId) {
        int rowUpdate = followRelationRepository.deleteByUserIdAndFollowerId(userId, followerId);
        if (rowUpdate != 1) {
            throw BusinessException.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message("Delete follow relation failed").build();
        }
    }
}
