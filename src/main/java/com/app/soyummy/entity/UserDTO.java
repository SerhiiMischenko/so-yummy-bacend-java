package com.app.soyummy.entity;

import lombok.Data;

@Data
public class UserDTO {
    private String name;
    private String email;
    private String avatar;
    private Long userId;
}
