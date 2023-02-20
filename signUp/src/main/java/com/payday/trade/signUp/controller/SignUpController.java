package com.payday.trade.signUp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.payday.trade.signUp.service.UsersService;

@RestController
@RequestMapping(value = "signUpAPI")
@RequiredArgsConstructor
public class SignUpController {

    private final UsersService usersService;

    @PostMapping(value = "/{userName}/{password}/{email}")
    public void registerNewUser(@PathVariable String userName, @PathVariable String password, @PathVariable String email) {
        usersService.createUser(userName, password, email);
    }
}
