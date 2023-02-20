package com.payday.trade.signIn.controller;

import com.payday.trade.signIn.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "signInAPI")
@RequiredArgsConstructor
public class SignInController {
    private final UsersService usersService;

    @GetMapping(value = "/{userName}/{password}")
    public ResponseEntity<String> logInUsers(@PathVariable String userName, @PathVariable String password) {
        return ResponseEntity.status(HttpStatus.OK).body(usersService.signIn(userName,password));
    }
}
