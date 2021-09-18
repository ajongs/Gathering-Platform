package com.gatheringplatform.service;

import com.gatheringplatform.domain.User;
import com.gatheringplatform.enums.ErrorEnum;
import com.gatheringplatform.exception.AccessTokenException;
import com.gatheringplatform.exception.RefreshTokenException;
import com.gatheringplatform.exception.RequestException;
import com.gatheringplatform.mapper.UserMapper;
import com.gatheringplatform.util.Jwt;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Jwt jwt;

    @Value("${accessToken}")
    private String accessToken;

    @Value("${refreshToken}")
    private String refreshToken;

    @Value("${token.header-name}")
    private String header;

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

    @Override
    public Map<String, String> logIn(User user) {
        //1.Mapper에서 DB user 가지고옴
        String inputId = user.getId();
        User dbUser = userMapper.getUserById(inputId);

        if(dbUser == null) {
            //로그인 아이디 틀림 exception
            throw new RequestException(ErrorEnum.INVALID_ID);
        }
        //2. 값비교
        if(!BCrypt.checkpw(user.getPw(), dbUser.getPw())){
            //비밀번호 오류
            throw new RequestException(ErrorEnum.INVALID_PW);
        }
        else{
            //3. 맞다면 토큰 발급
            Map<String, String> newToken = new HashMap<>();
            newToken.put(refreshToken, jwt.createToken(dbUser.getNickname(), dbUser.getId(), refreshToken));
            newToken.put(accessToken, jwt.createToken(dbUser.getNickname(), dbUser.getId(), accessToken));
            //4. 발급된 토큰 리턴
            return newToken;
        }

    }

    @Override
    public Map<String, String> refresh() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String token = request.getHeader(header);
        Boolean flag = jwt.validateToken(token, false);
        if(flag){
            //true는 access 토큰임. refresh() 를 요청할땐 refresh 토큰으로 요청해야함
            throw new RefreshTokenException(ErrorEnum.INVALID_REFRESHTOKEN);
        }
        else{
            Map<String, Object> payload = jwt.getPayload(token, false);
            Map<String, String> newAccessToken = new HashMap<>();
            newAccessToken.put(accessToken, jwt.createToken(payload.get("nickname").toString(), payload.get("id").toString(), accessToken));

            // refresh 토큰 만료 날짜가 7일이상 충분하지 않으면 refresh 토큰도 같이 발급
            if(!jwt.isEnoughExp(token, false)){
                newAccessToken.put(refreshToken, jwt.createToken(payload.get("nickname").toString(), payload.get("id").toString(), refreshToken));
            }
            return newAccessToken;
        }
    }

    @Override
    public String getLoginNickname() {
        Map<String, Object> payload = getTokenPayload();
        return payload.get("nickname").toString();
    }

    private Map<String, Object> getTokenPayload(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String token = request.getHeader(header);
        Boolean flag = jwt.validateToken(token, true);
        if(!flag){
            throw new AccessTokenException(ErrorEnum.INVALID_ACCESSTOKEN);
        }
        else{
            return jwt.getPayload(token, true);
        }
    }
}
