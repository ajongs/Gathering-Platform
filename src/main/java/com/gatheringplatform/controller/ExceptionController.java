package com.gatheringplatform.controller;

import com.gatheringplatform.enums.ErrorEnum;
import com.gatheringplatform.exception.DefaultException;
import com.gatheringplatform.exception.RequestException;
import com.gatheringplatform.format.DefaultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity defaultException(Throwable e){
        DefaultException defaultException = null;
        if(e instanceof DefaultException){
            defaultException = (DefaultException)e;
            defaultException.setDetailTrace(e.getStackTrace()[0].toString());
        }

        if(e instanceof MethodArgumentNotValidException){
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            defaultException = new DefaultException(ErrorEnum.VALIDATION_FAIL);
            defaultException.setErrorMessage(bindingResult.getFieldError().getDefaultMessage());
            defaultException.setDetailTrace(e.getStackTrace()[0].toString());
        }
        if(defaultException == null){
            e.printStackTrace();
            defaultException = new DefaultException(ErrorEnum.UNDEFINED_EXCEPTION);
        }
        return new ResponseEntity(defaultException, HttpStatus.BAD_REQUEST);
    }
}
