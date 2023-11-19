package com.app.soyummy.response;

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
