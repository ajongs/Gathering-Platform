package com.gatheringplatform.controller;


import com.gatheringplatform.annotation.ValidationGroups;
import com.gatheringplatform.domain.User;
import com.gatheringplatform.format.DefaultResponse;
import com.gatheringplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;
    //login
    @PostMapping(value="/logIn")
    public ResponseEntity login(@RequestBody @Validated(ValidationGroups.logIn.class) User user){
        return new ResponseEntity(userService.logIn(user), HttpStatus.OK);
    }
    //sign-up
    @PostMapping(value="/signUp")
    public ResponseEntity signUp(@RequestBody @Validated(ValidationGroups.signUp.class) User user){
        userService.signUp(user);
        return new ResponseEntity(new DefaultResponse("회원가입이 완료되었습니다.", HttpStatus.OK), HttpStatus.OK);
    }
}
