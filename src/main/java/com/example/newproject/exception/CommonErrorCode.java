package com.example.newproject.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"권한이 없습니다"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND,"리소스를 찾을수 없습니다"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"서버에서 에러가 발생하였습니다."),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST,"잘못된 파라미터입니다");
    private final HttpStatus httpStatus;
    private final String message;
}
