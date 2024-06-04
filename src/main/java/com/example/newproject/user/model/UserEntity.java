package com.example.newproject.user.model;

import lombok.Data;

@Data
public class UserEntity {
    private int iuser;
    private String email;
    private String password;
    private String nickname;
    private String gander;
    private String telNumber;
    private String address;
    private String birth;
    private String pic;
    private String createdAt;
}
