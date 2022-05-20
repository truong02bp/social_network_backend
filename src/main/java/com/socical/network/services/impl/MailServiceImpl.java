package com.socical.network.services.impl;

import com.socical.network.services.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;

    @Override
    public String sendOtp(String name, String mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        Random random = new Random();
        String otp = String.valueOf(random.nextInt(8999) + 1000);
        message.setFrom("Social_Network_Admin");
        message.setTo(mail);
        message.setSubject("Hi " + name + ", you have message from Social Network Admin");
        String content = "Hi " + name + "\n" + "Your verification code is : " + otp;
        message.setText(content);
        javaMailSender.send(message);
        return otp;
    }
}
