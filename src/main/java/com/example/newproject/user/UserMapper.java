package com.example.newproject.user;

import com.example.newproject.common.ResVo;
import com.example.newproject.user.model.UserEntity;
import com.example.newproject.user.model.UserSignupDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int signUp(UserSignupDto dto);

    int signPicUpd(UserSignupDto dto);

    String emailCheck(String email);

    String telCheck(String telNumber);

    UserEntity userEntity(String email);

}
