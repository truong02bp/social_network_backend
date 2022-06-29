package com.socical.network.services;

import com.socical.network.data.dto.MediaDto;
import com.socical.network.data.dto.ProfileDto;
import com.socical.network.data.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User create(User user);
    User findById(Long id);
    ProfileDto getProfileInformation(Long userId);

    User updateAvatar(MediaDto mediaDto);
}
