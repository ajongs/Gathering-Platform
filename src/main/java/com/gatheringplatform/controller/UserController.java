package com.gatheringplatform.controller;


import com.gatheringplatform.annotation.Auth;
import com.gatheringplatform.annotation.ValidationGroups;
import com.gatheringplatform.domain.User;
import com.gatheringplatform.format.DefaultResponse;
import com.gatheringplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Value("${accessToken}")
    private String access;

    @Value("${refreshToken}")
    private String refresh;

    @Autowired
    private UserService userService;
    //login
    @PostMapping(value="/logIn")
    public ResponseEntity login(@RequestBody @Validated(ValidationGroups.logIn.class) User user, HttpServletResponse response){
        return new ResponseEntity(userService.logIn(user), HttpStatus.OK);
    }
    //sign-up
    @PostMapping(value="/signUp")
    public ResponseEntity signUp(@RequestBody @Validated(ValidationGroups.signUp.class) User user){
        userService.signUp(user);
        return new ResponseEntity(new DefaultResponse("회원가입이 완료되었습니다.", HttpStatus.OK), HttpStatus.OK);
    }
    @Auth
    @PostMapping(value = "/check")
    public ResponseEntity check(){
        System.out.println("userhost check");
        return new ResponseEntity(new DefaultResponse("@Auth 잘됌", HttpStatus.OK), HttpStatus.OK);
    }
    @PostMapping(value = "/refresh")
    public ResponseEntity refresh(){
        return new ResponseEntity(userService.refresh(), HttpStatus.OK);
    }
}
