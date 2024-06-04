package com.example.newproject.user.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserSigninVo {
    private final int result;
    private int iuser;
    private String email;
    private String nickname;
    private String gender;
    private String telNumber;
    private String address;
    private String birth;
    private String pic;
    private String accessToken;
}
