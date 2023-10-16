package com.app.soyummy.response;

import com.app.soyummy.entity.User;
import lombok.Data;

@Data
public class ResponseData {
    private String token;
    private User user;

    public ResponseData(String token, User user ) {
        this.token = token;
        this.user = user;
    }
}
