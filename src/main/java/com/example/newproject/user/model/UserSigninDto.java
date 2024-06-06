package com.example.newproject.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserSigninDto {
    @Schema(title = "이메일", defaultValue = "ehdgusdl0808")
    private String email;
    @Schema(title = "비밀번호", defaultValue = "1234")
    private String password;
}
