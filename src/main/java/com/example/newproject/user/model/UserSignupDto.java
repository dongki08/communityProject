package com.example.newproject.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import org.springframework.web.multipart.MultipartFile;

@Data
@Schema(title = "회원가입Dto")
public class UserSignupDto {
    @JsonIgnore
    private int iuser;
    private String email;
    private String password;
    private String nickname;
    private int gander;
    @JsonIgnore
    private String pic;
    private String telNumber;
    private String address;
    private String birth;
    @JsonIgnore
    private MultipartFile file;
}
