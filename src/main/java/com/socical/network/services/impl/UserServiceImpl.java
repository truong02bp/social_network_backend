package com.socical.network.services.impl;

import com.socical.network.data.dto.MediaDto;
import com.socical.network.data.dto.MyUserDetails;
import com.socical.network.data.dto.ProfileDto;
import com.socical.network.data.dto.UserDto;
import com.socical.network.data.entities.Media;
import com.socical.network.data.entities.Role;
import com.socical.network.data.entities.User;
import com.socical.network.data.repositories.*;
import com.socical.network.exceptions.BusinessException;
import com.socical.network.exceptions.EmailInvalidException;
import com.socical.network.services.MediaService;
import com.socical.network.services.UserService;
import com.socical.network.common.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MediaService mediaService;
    private final RoleRepository roleRepository;
    private final PostRepository postRepository;
    private final FollowRelationRepository followRelationRepository;

    @Override
    public User create(User user) {
        if (!Validator.isValidEmail(user.getEmail())) {
            throw new EmailInvalidException(user.getEmail());
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        final String ANONYMOUS_IMAGE_NAME = "anonymous.png";
        Media anonymousImage = mediaService.findByName(ANONYMOUS_IMAGE_NAME).orElseThrow(() -> {
            throw BusinessException.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message("Can't set default avatar").build();
        });
        user.setAvatar(anonymousImage);

        final String DEFAULT_ROLE_CODE = "USER";
        Role roleDefault = roleRepository.findByCode(DEFAULT_ROLE_CODE).orElseThrow(() -> {
            throw BusinessException.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message("Can't set default role").build();
        });
        user.setRoles(List.of(roleDefault));
        user.setUsername(user.getName());
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.builder().status(HttpStatus.NOT_FOUND).message("Can't find user with id " + id).build();
        });
    }

    @Override
    public List<User> getByIds(List<Long> ids) {
        return userRepository.findAllById(ids);
    }

    @Override
    public int update(User user) {
        int rowUpdated = userRepository.update(user.getName(), user.getUsername(), user.getAddress(), user.getPhone(), user.getId());
        if (rowUpdated != 1) {
            throw BusinessException.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message("Update user failed").build();
        }
        return rowUpdated;
    }

    @Override
    public User updatePassword(UserDto userDto) {
        User user = findById(userDto.getId());
        if (!bCryptPasswordEncoder.matches(userDto.getOldPassword(), user.getPassword())) {
            throw BusinessException.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message("Old password don't match").build();
        }
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getNewPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.builder().message("User not found with id {" + id + "}").status(HttpStatus.NOT_FOUND).build();
        });
    }

    @Override
    public ProfileDto getProfileInformation(Long userId) {
        int posts = postRepository.countByUserId(userId);
        int followers = followRelationRepository.countFollowersByUserId(userId);
        int following = followRelationRepository.countFollowingByUserId(userId);
        return new ProfileDto(posts, followers, following);
    }

    @Override
    public User updateAvatar(MediaDto mediaDto) {
        Media media = mediaService.create(mediaDto);
        User user = findById(mediaDto.getUserId());
        user.setAvatar(media);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            throw new UsernameNotFoundException("Not found email : " + email);
        });
        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getCode())));
        return new MyUserDetails(user.getEmail(), user.getPassword(), true, true, true, true, authorities, user);
    }
}
