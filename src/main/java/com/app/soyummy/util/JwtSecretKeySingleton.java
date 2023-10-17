package com.app.soyummy.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.Getter;

import javax.crypto.SecretKey;
@Getter
@Data
public class JwtSecretKeySingleton {

    private static JwtSecretKeySingleton instance;
    private final SecretKey secretKey;

    private JwtSecretKeySingleton() {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public static JwtSecretKeySingleton getInstance() {
        if (instance == null) {
            instance = new JwtSecretKeySingleton();
        }
        return instance;
    }

}
