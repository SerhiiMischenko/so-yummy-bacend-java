package com.app.soyummy.response;

import com.app.soyummy.entity.User;
import com.app.soyummy.util.UserDTO;
import lombok.Data;

@Data
public class UserResponse {
    private String status;
    private String code;
    private ResponseData data;

    public UserResponse(ResponseData responseData) {
        this.status = "success";
        this.code = "200";
        this.data = responseData;
    }
}
