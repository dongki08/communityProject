package com.example.newproject.user;

import com.example.newproject.common.ResVo;
import com.example.newproject.user.model.UserSigninDto;
import com.example.newproject.user.model.UserSigninVo;
import com.example.newproject.user.model.UserSignupDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    @PostMapping(value = "/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "회원가입")
    public ResVo signup(@RequestPart(required = false)MultipartFile pic, @RequestPart UserSignupDto dto) {
        return service.signup(dto, pic);
    }

    @PostMapping("/signin")
    @Operation(summary = "로그인")
    public UserSigninVo signin(@RequestBody UserSigninDto dto, HttpServletRequest req, HttpServletResponse res) {
        return service.signin(dto, req, res);
    }
}
