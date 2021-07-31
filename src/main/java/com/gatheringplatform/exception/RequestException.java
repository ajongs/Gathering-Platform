package com.gatheringplatform.exception;

import com.gatheringplatform.enums.ErrorEnum;

public class RequestException extends DefaultException{
    public RequestException(ErrorEnum errorEnum){
        super(errorEnum);
    }
}
