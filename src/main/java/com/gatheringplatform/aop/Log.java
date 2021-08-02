package com.gatheringplatform.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Log {
    @Around("execution(* controller.*Controller.*(..))")
    public Object methodLogger(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.printf("%s 메소드 시작\n",joinPoint.getSignature().getName());
        try{
            Object result = joinPoint.proceed();
            return result;
        }finally {
            System.out.printf("%s 메소드 끝\n",joinPoint.getSignature().getName());
        }
    }
}
