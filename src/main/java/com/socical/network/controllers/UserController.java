package com.socical.network.controllers;

import com.socical.network.data.dto.MediaDto;
import com.socical.network.data.dto.MyUserDetails;
import com.socical.network.data.dto.ProfileDto;
import com.socical.network.data.dto.UserDto;
import com.socical.network.data.entities.User;
import com.socical.network.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<User> getCurrentUser() {
        MyUserDetails  myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(myUserDetails.getUser());
    }

    @GetMapping("/user/profile")
    public ResponseEntity<ProfileDto> getProfileInformation(@RequestParam("userId") Long userId) {
        return ResponseEntity.ok(userService.getProfileInformation(userId));
    }

    @PutMapping("/user/avatar")
    public ResponseEntity<User> updateAvatar(@RequestBody MediaDto mediaDto) {
        return ResponseEntity.ok(userService.updateAvatar(mediaDto));
    }

    @PostMapping("/user")
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.ok(userService.create(user));
    }

    @PutMapping("/user")
    public ResponseEntity<String> update(@RequestBody User user) {
        userService.update(user);
        return ResponseEntity.ok("Update success");
    }

    @PutMapping("/user/password")
    public ResponseEntity<User> updatePassword(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updatePassword(userDto));
    }
}
