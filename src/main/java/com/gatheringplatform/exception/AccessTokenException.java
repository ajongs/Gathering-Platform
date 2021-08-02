package com.gatheringplatform.exception;

import com.gatheringplatform.enums.ErrorEnum;

public class AccessTokenException extends DefaultException{
    public AccessTokenException(ErrorEnum errorEnum){
        super(errorEnum);
    };
}
