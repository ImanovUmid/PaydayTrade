package com.payday.trade.depositCash.controller;

import com.payday.trade.depositCash.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "depositCashAPI")
@RequiredArgsConstructor
public class DepositController {

    private final AccountService accountService;

    @PostMapping(value = "/add/{userId}")
    public void registerNewAccount(@PathVariable Long userId) {
        accountService.createAccount(userId);
    }

    @PutMapping(value = "/load/{userId}/{cash}")
    public void loadCashAccount(@PathVariable Long userId, @PathVariable Double cash) {
        accountService.loadCash(userId,cash);
    }
}
