package com.example.newproject.user;

import com.example.newproject.common.*;
import com.example.newproject.exception.RestApiException;
import com.example.newproject.exception.AllErrorCode;
import com.example.newproject.security.JwtTokenProvider;
import com.example.newproject.security.MyPrincipal;
import com.example.newproject.user.model.UserEntity;
import com.example.newproject.user.model.UserSigninDto;
import com.example.newproject.user.model.UserSigninVo;
import com.example.newproject.user.model.UserSignupDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final MyFileUtils myFileUtils;
    private final JwtTokenProvider jwtTokenProvider;
    private final CookieUtils cookieUtils;
    private final AppProperties appProperties;

    @Transactional
    public ResVo signup(UserSignupDto dto, MultipartFile pic) {
        String hashedPw = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(hashedPw);

        if(mapper.emailCheck(dto.getEmail()) != null) {
            throw new RestApiException(AllErrorCode.DUPLICATION_EMAIL);
        }

        if(mapper.telCheck(dto.getTelNumber()) != null) {
            throw new RestApiException(AllErrorCode.DUPLICATION_PHONE);
        }

        mapper.signUp(dto);

        if(pic != null) {
            String path = "/user/" + dto.getIuser();
            String savePicFileNm = myFileUtils.transferTo(pic, path);
            dto.setPic(savePicFileNm);
            mapper.signPicUpd(dto);
        }

        return new ResVo(dto.getIuser());
    }
    public UserSigninVo signin(UserSigninDto dto, HttpServletRequest req, HttpServletResponse res) {


        UserEntity entity = mapper.userEntity(dto.getEmail());

        if(entity == null || !passwordEncoder.matches(dto.getPassword(), entity.getPassword())) {
            throw new RestApiException(AllErrorCode.REGEXP_ID_PASSWORD);
        }

        MyPrincipal myPrincipal = MyPrincipal.builder()
                .iuser(entity.getIuser())
                .build();

        String at = jwtTokenProvider.generateAccessToken(myPrincipal);
        String rt = jwtTokenProvider.generateRefreshToken(myPrincipal);

        int rtCookieAgeMax = appProperties.getJwt().getRefreshTokenCookieMaxAge();
        cookieUtils.deleteCookie(res, "rt");
        cookieUtils.setCookie(res, "rt", rt, rtCookieAgeMax);


        return UserSigninVo.builder()
                .result(Const.SUCCESS)
                .iuser(entity.getIuser())
                .email(entity.getEmail())
                .nickname(entity.getNickname())
                .gender(entity.getGander())
                .telNumber(entity.getTelNumber())
                .address(entity.getAddress())
                .birth(entity.getBirth())
                .pic(entity.getPic())
                .accessToken(at)
                .build();
    }
}
