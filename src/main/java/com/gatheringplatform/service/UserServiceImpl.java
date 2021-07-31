package com.gatheringplatform.service;

import com.gatheringplatform.domain.User;
import com.gatheringplatform.enums.ErrorEnum;
import com.gatheringplatform.exception.RequestException;
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
        //id 존재여부
        if(userMapper.getIdById(user.getId()) != null){
            throw new RequestException(ErrorEnum.ID_ALREADY_EXISTS);
        }
        //닉네임 존재여부
        if(userMapper.getNicknameByNickname(user.getNickname()) != null){
            //TODO 닉네임 중복 exception
            System.out.println("닉네임 실행");
            throw new RequestException(ErrorEnum.NICKNAME_ALREADY_EXISTS);
        }
        //이메일 존재여부
        if(userMapper.getEmailByEmail(user.getEmail())!=null){
            //TODO 이메일 중복 exception
            System.out.println("이메일 중복 실행");
            throw new RequestException(ErrorEnum.EMAIL_ALREADY_EXISTS);
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
