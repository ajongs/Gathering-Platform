package com.gatheringplatform.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gatheringplatform.enums.ErrorEnum;

@JsonIgnoreProperties({"cause", "stackTrace", "message", "localizedMessage", "suppressed"})
public class DefaultException extends RuntimeException{
    private String className;
    private String code;
    private String errorMessage;
    private String detailTrace;

    public DefaultException(ErrorEnum errorEnum){
        this.code = errorEnum.getCode();
        this.errorMessage = errorEnum.getMessage();
        //DefaultException 클래스 넣어주기(하드코딩도 가능함 사실)
        className = this.getClass().getSimpleName();
    }
    public DefaultException(ErrorEnum errorEnum, String className){
        this.code = errorEnum.getCode();
        this.errorMessage = errorEnum.getMessage();
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getDetailTrace() {
        return detailTrace;
    }

    public void setDetailTrace(String detailTrace) {
        this.detailTrace = detailTrace;
    }
}
