package com.socical.network.controllers;

import com.socical.network.services.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping("/mail/otp")
    private ResponseEntity<String> sendOtp(@RequestParam("name") String name, @RequestParam("email") String email) {
        String otp = mailService.sendOtp(name, email);
        return ResponseEntity.ok(otp);
    }

}
