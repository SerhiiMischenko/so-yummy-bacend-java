package com.app.soyummy.controller;

import com.app.soyummy.repository.UserRepository;
import com.app.soyummy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Map<String, String> requestBody) {
        if(requestBody.get("name").isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "missing field name"));
        }
        if(requestBody.get("email").isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "missing field email"));
        }
        if(userRepository.findByUserEmail(requestBody.get("email")).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "Email in use"));
        }
        if(requestBody.get("password").isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "missing field password"));
        }

        userService.registerUser(requestBody.get("name"), requestBody.get("email"), requestBody.get("password"));

        return ResponseEntity.ok(Map.of("message", "User successfully registered"));
    }
}
