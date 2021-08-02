package com.gatheringplatform.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gatheringplatform.exception.AccessTokenException;
import com.gatheringplatform.mapper.UserMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64UrlCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class Jwt {

    @Autowired
    UserMapper userMapper;

    @Value("${accessToken}")
    String accessToken;

    public String createToken(String nickname, String id, String subject){
        String key = userMapper.getSalt(id);

        Calendar cal = Calendar.getInstance();
        if(subject.equals(accessToken)){
            cal.add(Calendar.HOUR, 1);
        }
        else{
            cal.add(Calendar.DATE, 3);
        }

        Map<String, Object> payload = new HashMap<>();
        payload.put("id",id);
        payload.put("nickname",nickname);

        Claims claims = Jwts.claims(payload)
                .setExpiration(new Date(cal.getTimeInMillis()))
                .setIssuedAt(new Date())
                .setSubject(subject)
                .setIssuer("GatheringPlatform");


        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, key.getBytes())
                .setHeaderParam("typ","JWT")
                .setClaims(claims)
                .compact();
    }
    public Boolean validateToken(String token, boolean flag){
        if(!token.isEmpty()){
            //Claims claims = Jwts.parser().setSigningKey()
        }
        return true;
    }
    private Map<String, Object> getPayload(String token, boolean flag){
        String splitToken = token.split("\\.")[1];
        //검사해서 1번째만 파싱
        ObjectMapper ob = new ObjectMapper();
        Map<String, Object> payload = null;
        //base64URL로 디코딩
        try {
            payload = ob.readValue(Base64UrlCodec.BASE64.decode(splitToken), Map.class);
        } catch (IOException e) {
            if(!flag){
                //throw new AccessTokenException();
            }
            //else
                //throw new RefreshTokenException();
        }
        return null;
    }
}
