package com.socical.network.services;

import com.socical.network.data.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User create(User user);
}
