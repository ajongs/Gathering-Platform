package com.gatheringplatform.enums;

import org.springframework.http.HttpStatus;

public enum ErrorEnum {
    NICKNAME_ALREADY_EXISTS("SIGNUP01","이미 존재하는 닉네임입니다.",HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXISTS("SIGNUP_02", "이미 가입되어 있는 이메일입니다.", HttpStatus.BAD_REQUEST)
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
