package com.gatheringplatform.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gatheringplatform.enums.ErrorEnum;
import com.gatheringplatform.exception.AccessTokenException;
import com.gatheringplatform.exception.RefreshTokenException;
import com.gatheringplatform.mapper.UserMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.Base64UrlCodec;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class Jwt {

    @Autowired
    private UserMapper userMapper;

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
        //TODO TRUE ACCESS
        //TODO FALSE REFRESH
        System.out.println("ValidationToken 호출");
        Map<String, Object> payload = getPayload(token, flag);
        System.out.println("payload id : "+payload.get("id"));
        String key = userMapper.getSalt(payload.get("id").toString());
        if(!token.isEmpty()){
            try{
                Claims claims = Jwts.parser().setSigningKey(key.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody();
            }catch (SignatureException e){
                if(flag==true){ //access
                    throw new AccessTokenException(ErrorEnum.INVALID_ACCESSTOKEN_SIGNATURE);
                }
                else
                    throw new RefreshTokenException(ErrorEnum.INVALID_REFRESHTOKEN_SIGNATRUE);
            }catch(ExpiredJwtException e){
                if(flag==true){ //access
                    throw new AccessTokenException(ErrorEnum.EXPRIED_ACCESSTOKEN);
                }
                else
                    throw new RefreshTokenException(ErrorEnum.EXPRIED_REFRESHTOKEN);
            }catch(MalformedJwtException e){
                if(flag==true){ //access
                    throw new AccessTokenException(ErrorEnum.MALFORMED_ACCESSTOKEN);
                }
                else
                    throw new RefreshTokenException(ErrorEnum.MALFORMED_REFRESHTOKEN);
            }
        }
        return true;
    }
    private Map<String, Object> getPayload(String token, boolean flag){
        //TODO TRUE ACCESS
        //TODO FALSE REFRESH
        System.out.println("getPayload 호출-----");
        String splitToken = token.split("\\.")[1];
        System.out.println("split : "+splitToken);
        //검사해서 1번째만 파싱
        ObjectMapper ob = new ObjectMapper();
        Map<String, Object> payload = null;
        //base64URL로 디코딩
        try {
            payload = ob.readValue(new String(Base64.decodeBase64(splitToken)), Map.class);
            System.out.println("getPayload : "+payload);
        } catch (IOException e) {
            if(!flag){
                throw new RefreshTokenException(ErrorEnum.INVALID_REFRESHTOKEN);
            }
            else
                throw new AccessTokenException(ErrorEnum.INVALID_ACCESSTOKEN);
        }
        return payload;
    }
}
