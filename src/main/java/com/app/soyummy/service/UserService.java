package com.app.soyummy.service;

import com.app.soyummy.entity.User;
import com.app.soyummy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User registerUser(String userName, String userEmail, String userPassword) {
        User newUser = new User();
        newUser.setUserName(userName);
        newUser.setUserEmail(userEmail);
        newUser.setUserPassword(userPassword);
        return userRepository.save(newUser);
    }
}
