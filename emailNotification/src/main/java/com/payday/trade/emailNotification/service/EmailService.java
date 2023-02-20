package com.payday.trade.emailNotification.service;

public interface EmailService {
    void sendEmail(boolean result, Long userId);
}
