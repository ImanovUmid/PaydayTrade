package com.payday.trade.depositCash.service;

public interface AccountService {
    void createAccount(Long userId);
    void loadCash(Long userId, Double cash);
}
