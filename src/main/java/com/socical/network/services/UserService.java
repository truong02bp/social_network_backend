package com.socical.network.services;

import com.socical.network.data.dto.MediaDto;
import com.socical.network.data.dto.ProfileDto;
import com.socical.network.data.dto.UserDto;
import com.socical.network.data.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User create(User user);
    User getById(Long id);
    List<User> getByIds(List<Long> ids);
    int update(User user);
    User updatePassword(UserDto userDto);
    User findById(Long id);
    ProfileDto getProfileInformation(Long userId);

    User updateAvatar(MediaDto mediaDto);
}
