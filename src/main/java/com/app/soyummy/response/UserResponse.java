package com.app.soyummy.response;

import com.app.soyummy.entity.User;
import lombok.Data;

@Data
public class UserResponse {
    private String status;
    private String code;
    private ResponseData data;

    public UserResponse(ResponseData responseData) {
        this.status = "200";
        this.code = "success";
        this.data = responseData;
    }
}
