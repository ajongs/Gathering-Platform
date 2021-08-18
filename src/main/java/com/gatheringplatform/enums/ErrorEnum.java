package com.gatheringplatform.enums;

import org.springframework.http.HttpStatus;

public enum ErrorEnum {
    UNDEFINED_EXCEPTION("UNDEFINED", "서버에서 정의되지 않은 오류입니다.",HttpStatus.INTERNAL_SERVER_ERROR),
    NICKNAME_ALREADY_EXISTS("SIGNUP_01","이미 존재하는 닉네임입니다.",HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXISTS("SIGNUP_02", "이미 가입되어 있는 이메일입니다.", HttpStatus.BAD_REQUEST),
    ID_ALREADY_EXISTS("SIGNUP_03", "이미 존재하는 아이디입니다.", HttpStatus.BAD_REQUEST),
    VALIDATION_FAIL("SIGNUP_04", "유효성 검사 실패하였습니다.", HttpStatus.BAD_REQUEST),

    INVALID_ID("LOGIN_01", "유효하지 않은 아이디입니다", HttpStatus.BAD_REQUEST),
    INVALID_PW("LOGIN_02", "비밀번호가 올바르지 않습니다.", HttpStatus.BAD_REQUEST),

    UNAUTHORIZED("UNAUTHORIZED", "요청 권한이 없습니다.", HttpStatus.UNAUTHORIZED),


    INVALID_ACCESSTOKEN("ACTOKEN_01", "유효하지 않은 AccessToken 입니다.", HttpStatus.UNAUTHORIZED),
    INVALID_ACCESSTOKEN_SIGNATURE("ACTOKEN_02", "AccessToken의 서명이 올바르지 않습니다.", HttpStatus.UNAUTHORIZED),
    EXPRIED_ACCESSTOKEN("ACTOKEN_03", "AccessToken 이 만료되었습니다.", HttpStatus.UNAUTHORIZED),
    MALFORMED_ACCESSTOKEN("ACTOKEN_04", "올바르지 않은 AccessToken 의 구조입니다.", HttpStatus.UNAUTHORIZED),
    ACCESSTOKEN_NULL("ACTOKEN_05", "요청하신 AccessToken 이 비어있습니다. 헤더를 확인하세요", HttpStatus.FORBIDDEN),

    INVALID_REFRESHTOKEN("RETOKEN_01", "유효하지 않은 RefershToken 입니다.", HttpStatus.UNAUTHORIZED),
    INVALID_REFRESHTOKEN_SIGNATRUE("RETOKEN_02", "RefreshToken 의 서명이 올바르지 않습니다.", HttpStatus.UNAUTHORIZED),
    EXPRIED_REFRESHTOKEN("RETOKEN_03", "RefreshToken 이 만료되었습니다.", HttpStatus.UNAUTHORIZED),
    MALFORMED_REFRESHTOKEN("RETOKEN_04", "올바르지 않은 RefreshToken 의 구조입니다.", HttpStatus.UNAUTHORIZED),
    REFRESHTOKEN_NULL("RETOKEN_05", "요청하신 RefreshToken 이 비어있습니다. 헤더를 확인하세요", HttpStatus.FORBIDDEN),

    //aws
    THUMBNAIL_NULL("AWSS3_01","사진을 선택하지 않으셨습니다. 사진을 선택해주세요. ",HttpStatus.BAD_REQUEST),
    INVALID_THUMBNAIL("AWSS3_02", "유효하지 않은 사진 파일 확장자입니다.", HttpStatus.BAD_REQUEST),
    IMAGES_NULL("AWSS3_03","사진을 선택하지 않으셨습니다. 사진을 선택해주세요. ",HttpStatus.BAD_REQUEST),
    INVALID_IMAGE("AWSS3_04", "유효하지 않은 사진 파일 확장자입니다.", HttpStatus.BAD_REQUEST),

    //page
    NON_EXISTED_PAGE("PAGE_01", "존재하지 않는 페이지입니다.", HttpStatus.BAD_REQUEST),
    INVALID_CATEGORY("PAGE_02", "유효하지 않은 카테고리입니다.", HttpStatus.BAD_REQUEST),
    DELETED_PAGE("PAGE_03", "삭제되었거나 존재하지 않는 페이지입니다.", HttpStatus.BAD_REQUEST)
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
