package com.gatheringplatform.controller;


import com.gatheringplatform.annotation.Auth;
import com.gatheringplatform.annotation.ValidationGroups;
import com.gatheringplatform.domain.User;
import com.gatheringplatform.format.DefaultResponse;
import com.gatheringplatform.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
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
    @ApiOperation(value = "로그인", notes = "로그인 api입니다.\n 로그인이 완료되면 access token과 refresh token이 발급됩니다. \n* Id와 Pw만 입력하시면 됩니다. *")
    public ResponseEntity login(@RequestBody @Validated(ValidationGroups.logIn.class) User user, HttpServletResponse response){
        return new ResponseEntity(userService.logIn(user), HttpStatus.OK);
    }
    //sign-up
    @PostMapping(value="/signUp")
    @ApiOperation(value = "회원가입", notes = "회원가입을 하는 api입니다.")
    public ResponseEntity signUp(@RequestBody @Validated(ValidationGroups.signUp.class) User user){
        userService.signUp(user);
        return new ResponseEntity(new DefaultResponse("회원가입이 완료되었습니다.", HttpStatus.OK), HttpStatus.OK);
    }
    @Auth
    @PostMapping(value = "/check")
    @ApiOperation(value = "토큰을 사용해서 api호출이 되는지 확인", notes = "access token을 이용하여 로그인이 필요한 api가 잘 호출되는 지 확인하는 api입니다.",
    authorizations = @Authorization(value = "JWT"))
    public ResponseEntity check(){
        return new ResponseEntity(new DefaultResponse("@Auth 잘됌", HttpStatus.OK), HttpStatus.OK);
    }
    @PostMapping(value = "/refresh")
    @ApiOperation(value = "토큰 재발급", notes = "refresh 토큰을 이용하여 access token을 재발급 받는 api입니다.\n만약 refresh 토큰의 유효기간이 7일미만 이라면 refresh 토큰도 함께 발급됩니다.")
    public ResponseEntity refresh(){
        return new ResponseEntity(userService.refresh(), HttpStatus.OK);
    }
}
