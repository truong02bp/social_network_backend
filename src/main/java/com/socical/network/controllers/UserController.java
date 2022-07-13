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

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<User> getCurrentUser() {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(myUserDetails.getUser());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/user/ids")
    public ResponseEntity<List<User>> getByIds(@RequestParam("ids") List<Long> ids) {
        return ResponseEntity.ok(userService.getByIds(ids));
    }

    @GetMapping("/user/details")
    public ResponseEntity<MyUserDetails> getCurrentUserDetails() {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(myUserDetails);
    }

    @GetMapping("/user/profile")
    public ResponseEntity<ProfileDto> getProfileInformation(@RequestParam("userId") Long userId) {
        return ResponseEntity.ok(userService.getProfileInformation(userId));
    }

    @PostMapping("/user")
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.ok(userService.create(user));
    }

    @PutMapping("/user/avatar")
    public ResponseEntity<User> updateAvatar(@RequestBody MediaDto mediaDto) {
        return ResponseEntity.ok(userService.updateAvatar(mediaDto));
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
