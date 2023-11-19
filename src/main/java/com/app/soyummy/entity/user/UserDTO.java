package com.app.soyummy.entity.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private String name;
    private String email;
    private String avatar;
    private String userId;
}
