package com.socical.network.services.impl;

import com.socical.network.data.dto.MyUserDetails;
import com.socical.network.data.entities.FollowRelation;
import com.socical.network.data.entities.FollowRequest;
import com.socical.network.data.entities.User;
import com.socical.network.data.repositories.FollowRelationRepository;
import com.socical.network.data.repositories.FollowRequestRepository;
import com.socical.network.exceptions.BusinessException;
import com.socical.network.services.FollowRequestService;
import com.socical.network.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowRequestServiceImpl implements FollowRequestService {

    private final FollowRequestRepository followRequestRepository;
    private final FollowRelationRepository followRelationRepository;
    private final UserService userService;

    @Override
    public List<FollowRequest> getByUserId(Long userId) {
        return followRequestRepository.findByUserId(userId);
    }

    @Override
    public int countByUserId(Long userId) {
        return followRequestRepository.countByUserId(userId);
    }

    @Override
    public FollowRequest create(Long receiverId) {
        FollowRequest followRequest = new FollowRequest();
        User sender = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        User receiver = userService.findById(receiverId);
        followRequest.setReceiver(receiver);
        followRequest.setSender(sender);
        return followRequestRepository.save(followRequest);
    }

    @Override
    public void delete(Long id) {
        followRequestRepository.deleteById(id);
    }

    @Override
    public void acceptFollowRequest(Long id) {
        FollowRequest followRequest = followRequestRepository.findById(id).orElseThrow(
            () -> {
              throw BusinessException.builder().message("No follow request with id " + id).status(HttpStatus.NOT_FOUND).build();
            }
        );
        FollowRelation followRelation = new FollowRelation();
        followRelation.setFollower(followRequest.getSender());
        followRelation.setUser(followRequest.getReceiver());
        followRelationRepository.save(followRelation);
        followRequestRepository.delete(followRequest);
    }

}
