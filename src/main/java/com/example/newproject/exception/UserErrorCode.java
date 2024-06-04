package com.example.newproject.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode{


    NOT_EMAIL(HttpStatus.BAD_REQUEST,"아이디를 입력해주세요"),
    REGEXP_EMAIL(HttpStatus.BAD_REQUEST,"이메일 형식이 틀렸습니다"),
    REGEXP_TEL(HttpStatus.BAD_REQUEST,"전화번호 형식이 틀렸습니다"),
    REGEXP_BIRTH(HttpStatus.BAD_REQUEST,"생년월일 형식이 틀렸습니다"),
    REGEXP_GENDER(HttpStatus.BAD_REQUEST,"성별을 선택해 주세요"),
    NOT_PASSWORD(HttpStatus.BAD_REQUEST,"비밀번호를 입력해 주세요"),
    NOT_NAME(HttpStatus.BAD_REQUEST,"이름을 입력해주세요"),
    NOT_NICK_NAME(HttpStatus.BAD_REQUEST,"닉네임을 입력해 주세요"),
    NOT_GENDER(HttpStatus.BAD_REQUEST,"성별을 선택해 주세요"),
    NOT_TEL(HttpStatus.BAD_REQUEST,"전화번호를 입력해 주세요"),
    DUPLICATION_NICK_NAME(HttpStatus.BAD_REQUEST,"중복된 닉네임 입니다"),
    DUPLICATION_EMAIL(HttpStatus.BAD_REQUEST,"중복된 이메일 입니다"),
    DUPLICATION_PHONE(HttpStatus.BAD_REQUEST,"중복된 전화번호 입니다"),
    REGEXP_ID_PASSWORD(HttpStatus.BAD_REQUEST,"잘못된 입력입니다.");


    private final HttpStatus httpStatus;
    private final String message;
}
