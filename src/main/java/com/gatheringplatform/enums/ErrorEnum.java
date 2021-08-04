package com.gatheringplatform.enums;

import org.springframework.http.HttpStatus;

public enum ErrorEnum {
    NICKNAME_ALREADY_EXISTS("SIGNUP01","이미 존재하는 닉네임입니다.",HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXISTS("SIGNUP_02", "이미 가입되어 있는 이메일입니다.", HttpStatus.BAD_REQUEST),
    ID_ALREADY_EXISTS("SIGNUP_03", "이미 존재하는 아이디입니다.", HttpStatus.BAD_REQUEST),
    VALIDATION_FAIL("SIGNUP_04", "유효성 검사 실패하였습니다.", HttpStatus.BAD_REQUEST),

    INVALID_ID("LOGIN_01", "유효하지 않은 아이디입니다", HttpStatus.BAD_REQUEST),
    INVALID_PW("LOGIN_02", "비밀번호가 올바르지 않습니다.", HttpStatus.BAD_REQUEST),


    INVALID_ACCESSTOKEN("ACTOKEN_01", "유효하지 않은 AccessToken 입니다.", HttpStatus.BAD_REQUEST),
    INVALID_ACCESSTOKEN_SIGNATURE("ACTOKEN_02", "AccessToken의 서명이 올바르지 않습니다.", HttpStatus.BAD_REQUEST),
    EXPRIED_ACCESSTOKEN("ACTOKEN_03", "AccessToken 이 만료되었습니다.", HttpStatus.BAD_REQUEST),
    MALFORMED_ACCESSTOKEN("ACTOKEN_04", "올바르지 않은 AccessToken 의 구조입니다.", HttpStatus.BAD_REQUEST),
    ACCESSTOKEN_NULL("ACTOKEN_05", "요청하신 AccessToken 이 비어있습니다. 헤더를 확인하세요", HttpStatus.BAD_REQUEST),

    INVALID_REFRESHTOKEN("RETOKEN_01", "유효하지 않은 RefershToken 입니다.", HttpStatus.BAD_REQUEST),
    INVALID_REFRESHTOKEN_SIGNATRUE("RETOKEN_02", "RefreshToken 의 서명이 올바르지 않습니다.", HttpStatus.BAD_REQUEST),
    EXPRIED_REFRESHTOKEN("RETOKEN_03", "RefreshToken 이 만료되었습니다.", HttpStatus.BAD_REQUEST),
    MALFORMED_REFRESHTOKEN("RETOKEN_04", "올바르지 않은 RefreshToken 의 구조입니다.", HttpStatus.BAD_REQUEST),
    REFRESHTOKEN_NULL("RETOKEN_05", "요청하신 RefreshToken 이 비어있습니다. 헤더를 확인하세요", HttpStatus.BAD_REQUEST)
    ;


    private String code;
    private String message;
    private HttpStatus httpStatus;

    ErrorEnum(String code, String message, HttpStatus httpStatus){
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
