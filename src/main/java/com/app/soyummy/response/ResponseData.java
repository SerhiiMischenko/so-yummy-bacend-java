package com.app.soyummy.response;

import com.app.soyummy.util.UserDTO;
import lombok.Data;

@Data
public class ResponseData {
    private String token;
    private UserDTO user;

    public ResponseData(String token, UserDTO user ) {
        this.token = token;
        this.user = user;
    }
}
