package com.payday.trade.emailNotification.controller;

import com.payday.trade.emailNotification.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "emailNotificationAPI")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @GetMapping(value = "/{result}/{userId}")
    public void sendMail(@PathVariable boolean result, @PathVariable Long userId) {
        emailService.sendEmail(result, userId);
    }
}
