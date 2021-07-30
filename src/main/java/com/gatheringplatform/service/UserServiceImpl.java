package com.gatheringplatform.service;

import com.gatheringplatform.domain.User;
import com.gatheringplatform.mapper.UserMapper;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    @Override
    public void signUp(User user) {
        //닉네임 존재여부
        if(userMapper.getNicknameByNickname(user.getNickname())!= null){
            //TODO 닉네임 중복 exception
        }
        //이메일 존재여부
        if(userMapper.getEmailByEmail(user.getEmail())!=null){
            //TODO 이메일 중복 exception
        }

        //비밀번호 암호화
        user.setPw(BCrypt.hashpw(user.getPw(), BCrypt.gensalt()));

        //솔트값 만들기
        String salt = UUID.randomUUID().toString() + new Date().toString();
        user.setSalt(salt);

        //mapper 호출
        userMapper.signUp(user);
    }
}
