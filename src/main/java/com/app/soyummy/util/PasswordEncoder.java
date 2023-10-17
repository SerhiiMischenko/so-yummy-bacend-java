package com.app.soyummy.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String passwordEncode(String password) {
        return passwordEncoder.encode(password);
    }
}