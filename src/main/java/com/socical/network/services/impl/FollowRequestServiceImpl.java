package com.socical.network.services.impl;

import com.socical.network.data.dto.MyUserDetails;
import com.socical.network.data.entities.FollowRequest;
import com.socical.network.data.entities.User;
import com.socical.network.data.repositories.FollowRequestRepository;
import com.socical.network.services.FollowRequestService;
import com.socical.network.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowRequestServiceImpl implements FollowRequestService {

    private final FollowRequestRepository followRequestRepository;
    private final UserService userService;

    @Override
    public FollowRequest create(Long receiverId) {
        FollowRequest followRequest = new FollowRequest();
        User sender = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        User receiver = userService.findById(receiverId);
        followRequest.setReceiver(receiver);
        followRequest.setSender(sender);
        return followRequestRepository.save(followRequest);
    }

}
