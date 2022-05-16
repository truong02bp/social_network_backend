package com.socical.network.services.impl;

import com.socical.network.data.entities.User;
import com.socical.network.data.repositories.UserRepository;
import com.socical.network.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

}
