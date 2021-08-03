package com.gatheringplatform.exception;

import com.gatheringplatform.enums.ErrorEnum;

public class RefreshTokenException extends DefaultException{
    public RefreshTokenException(ErrorEnum errorEnum){
       super(errorEnum);
    }
}
