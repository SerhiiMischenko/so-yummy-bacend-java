package com.app.soyummy.response;

import lombok.Data;

@Data
public class TokenInit {
    private final String token;
    public TokenInit() {
        this.token = "Im token";

    }
}
