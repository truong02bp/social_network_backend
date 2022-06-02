package com.socical.network.services.impl;

import com.socical.network.data.entities.User;
import com.socical.network.data.repositories.FollowRelationRepository;
import com.socical.network.services.FollowRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowRelationServiceImpl implements FollowRelationService {

    private final FollowRelationRepository followRelationRepository;

    @Override
    public List<User> findFollowingByUserId(Long userId) {
        return followRelationRepository.findFollowingByUserId(userId);
    }

    @Override
    public List<User> findFollowersByUserId(Long userId) {
        return followRelationRepository.findFollowersByUserId(userId);
    }
}
