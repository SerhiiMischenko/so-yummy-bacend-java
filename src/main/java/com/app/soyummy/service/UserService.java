package com.app.soyummy.service;

import com.app.soyummy.entity.User;
import com.app.soyummy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public ResponseEntity<?> registerUser(Map<String, String> requestBody) {
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

        User newUser = new User();
        newUser.setUserName(requestBody.get("name"));
        newUser.setUserEmail(requestBody.get("email"));
        newUser.setUserPassword(requestBody.get("password"));

        userRepository.save(newUser);

        return ResponseEntity.ok(Map.of("message", "User successfully registered"));
    }
}
