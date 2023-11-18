package com.app.soyummy.service;

import com.app.soyummy.entity.User;
import com.app.soyummy.util.JwtTokenProvider;
import com.app.soyummy.util.PasswordEncoder;
import com.app.soyummy.util.UserDTO;
import com.app.soyummy.repository.UserRepository;
import com.app.soyummy.response.ResponseData;
import com.app.soyummy.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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
        newUser.setId(UUID.randomUUID().toString());
        newUser.setUserName(requestBody.get("name"));
        newUser.setUserEmail(requestBody.get("email"));
        newUser.setUserPassword(PasswordEncoder.passwordEncode(requestBody.get("password")));
        newUser.setToken(new JwtTokenProvider().generateToken(newUser.getUserName()));

        userRepository.save(newUser);

        UserDTO userDTO = new UserDTO();
        userDTO.setName(newUser.getUserName());
        userDTO.setEmail(newUser.getUserEmail());
        userDTO.setAvatar(newUser.getAvatar());
        userDTO.setUserId(newUser.getId());

        UserResponse userResponse = new UserResponse(new ResponseData(
                newUser.getToken(), userDTO));

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    public ResponseEntity<?> login(Map<String, String> requestBody) {
        if(requestBody.get("email") == null || requestBody.get("email").isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "missing field email"));
        }
        if(requestBody.get("password") == null || requestBody.get("password").isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "missing field password"));
        }

        Optional<User> userOptional = userRepository.findByUserEmail(requestBody.get("email"));
        User user = userOptional.orElse(null);

        if(user == null) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", "Email invalid"));
        }
        String passwordFromRequest = requestBody.get("password");
        String hashedPasswordFromDatabase = user.getUserPassword();
        boolean passwordMatches = BCrypt.checkpw(passwordFromRequest, hashedPasswordFromDatabase);

        if(!passwordMatches) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", "Password invalid"));
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getUserName());
        userDTO.setEmail(user.getUserEmail());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setUserId(user.getId());

        UserResponse userResponse = new UserResponse(new ResponseData(user.getToken(), userDTO));

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    public ResponseEntity<?> logout(Map<String, String> requestBody) {
        User findUser = userRepository.findUserByToken(requestBody.get("token"));

        if(findUser == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Not authorized"));
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> current(String authorizationHeader) {
        String jwtToken = authorizationHeader.replace("Bearer ", "");
        System.out.println(jwtToken);
        User findUser = userRepository.findUserByToken(jwtToken);
        System.out.println(findUser);

        if(findUser == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Not authorized"));
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setName(findUser.getUserName());
        userDTO.setEmail(findUser.getUserEmail());
        userDTO.setAvatar(findUser.getAvatar());
        userDTO.setUserId(findUser.getId());

        UserResponse userResponse = new UserResponse(new ResponseData(userDTO));

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
}
