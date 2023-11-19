package com.app.soyummy.response;

import com.app.soyummy.entity.user.UserDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData {
    private String token;
    private UserDTO user;

    public ResponseData(String token, UserDTO user ) {
        this.token = token;
        this.user = user;
    }

    public ResponseData(UserDTO user) {
        this.user = user;
    }
}
