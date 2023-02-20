package com.payday.trade.signUp.controller;

import com.payday.trade.signUp.service.UserCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "signUpAPI/userActivate")
@RequiredArgsConstructor
public class UserActivationController {

    private final UserCheckService userCheckService;

    @GetMapping(value = "/{sessionKey}")
    public void activateUser(@PathVariable String sessionKey) {
        userCheckService.checkActivate(sessionKey);
    }
}
