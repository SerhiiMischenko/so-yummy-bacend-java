package com.app.soyummy.controller;

import com.app.soyummy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/auth/signup")
    public ResponseEntity<?> signup(@RequestBody Map<String, String> requestBody) {
        return userService.registerUser(requestBody);
    }

    @PostMapping("/auth/login")
        public ResponseEntity<?> login(@RequestBody Map<String, String> requestBody) {
            return userService.login((requestBody));
        }

    @GetMapping("/auth/logout")
    public ResponseEntity<?> logout(@RequestBody Map<String, String> requestBody) {
        return userService.logout((requestBody));
    }

    @GetMapping("/auth/current")
    public ResponseEntity<?> current(@RequestBody Map<String, String> requestBody) {
        return userService.current((requestBody));
    }
    }
