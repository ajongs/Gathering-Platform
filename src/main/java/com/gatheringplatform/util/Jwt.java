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

    @Value("${refreshToken}")
    String refreshToken;

    public String createToken(String nickname, String id, String subject){
        String key = userMapper.getSalt(id);

        Calendar cal = Calendar.getInstance();
        if(subject.equals(accessToken)){
            cal.add(Calendar.HOUR, 1);
        }
        else{
            cal.add(Calendar.DATE, 8);
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
        Map<String, Object> payload = getPayload(token, flag);
        String key = userMapper.getSalt(payload.get("id").toString());
        System.out.println("-----------------"+key);


        if(!token.isEmpty()){
            try{
                Claims claims = Jwts.parser().setSigningKey(key.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody();
                String subject = claims.getSubject();

                // accessToken 검증요청에 refreshToken 이 들어오는 경우
                if(flag==true && subject.equals(refreshToken)){ //access Token 일때
                    throw new AccessTokenException(ErrorEnum.INVALID_ACCESSTOKEN);
                }
                // refreshToken 검증요청에 accessToken 이 들어오는 경우
                else if(flag==false && subject.equals(accessToken)){
                    throw new RefreshTokenException(ErrorEnum.INVALID_REFRESHTOKEN);
                }
                else if(flag==true && subject.equals(accessToken)){
                    //반환값 트루이면 accessToken valid
                    return true;
                }
                else //반환값 false 이면 refreshToken valid
                    return false;

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
        else{
            if(flag ==true){
                //accessToken null
                throw new AccessTokenException(ErrorEnum.ACCESSTOKEN_NULL);
            }
            else{
                //refreshToken null
                throw new RefreshTokenException(ErrorEnum.REFRESHTOKEN_NULL);
            }
        }
    }
    public Map<String, Object> getPayload(String token, boolean flag){
        //TODO TRUE ACCESS
        //TODO FALSE REFRESH
        String splitToken = token.split("\\.")[1];
        //검사해서 1번째만 파싱
        ObjectMapper ob = new ObjectMapper();
        Map<String, Object> payload = null;
        //base64로 디코딩
        try {
            payload = ob.readValue(new String(Base64.decodeBase64(splitToken)), Map.class);
        } catch (IOException e) {
            if(!flag){
                throw new RefreshTokenException(ErrorEnum.INVALID_REFRESHTOKEN);
            }
            else
                throw new AccessTokenException(ErrorEnum.INVALID_ACCESSTOKEN);
        }
        return payload;
    }
    public boolean isEnoughExp(String token, boolean flag){
        //현재 시간 기준으로 7일뒤 날짜
        long enoughDate = System.currentTimeMillis() + 1000*60*60*24*7;

        //토큰에서 얻어온 만료날짜
        long expDate =Long.parseLong( this.getPayload(token, flag).get("exp").toString() ) * 1000;

        if(enoughDate > expDate ) {
            //expDate 가 7일보다 적게 남음
            return false;
        }
        else{
            //expDate 충분함
            return true;
        }
    }
}
