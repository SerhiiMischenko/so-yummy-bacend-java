package com.app.soyummy.entity.user;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {
    @Id
    private String id;
    private String userName;
    @Column(unique = true)
    private String userEmail;
    private String userPassword;
    private String avatar = "https://res.cloudinary.com/db5awxaxs/image/upload/v1680438156/cld-sample-4.jpg";
    private String token;
}
