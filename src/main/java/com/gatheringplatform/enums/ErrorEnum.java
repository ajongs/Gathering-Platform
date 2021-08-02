package com.gatheringplatform.enums;

import org.springframework.http.HttpStatus;

public enum ErrorEnum {
    NICKNAME_ALREADY_EXISTS("SIGNUP01","이미 존재하는 닉네임입니다.",HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXISTS("SIGNUP_02", "이미 가입되어 있는 이메일입니다.", HttpStatus.BAD_REQUEST),
    ID_ALREADY_EXISTS("SIGNUP_03", "이미 존재하는 아이디입니다.", HttpStatus.BAD_REQUEST),
    VALIDATION_FAIL("SIGNUP_04", "유효성 검사 실패하였습니다.", HttpStatus.BAD_REQUEST),

    INVALID_ID("LOGIN_01", "유효하지 않은 아이디입니다", HttpStatus.BAD_REQUEST),
    INVALID_PW("LOGIN_02", "비밀번호가 올바르지 않습니다.", HttpStatus.BAD_REQUEST),

    INVALID_ACCESSTOKEN("ACTOKEN_01", "유효하지 않은 AccessToken 입니다.", HttpStatus.BAD_REQUEST);

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
