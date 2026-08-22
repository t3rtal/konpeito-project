package com.tertal.konpeito.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tertal.konpeito.dto.UserDto;
import com.tertal.konpeito.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody UserDto user
    ) {
        this.userService.registerUser(user);
        return ResponseEntity.ok("Registration succeeded");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody UserDto user
    ) {
        String authToken = this.userService.verifyUser(user);
        return ResponseEntity.ok(authToken);
    }

}
