package com.gatheringplatform.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWT {
    private String key = "whatSecretKey";

    public String createToken(String name, String id, String subject){
        Calendar cal = Calendar.getInstance();
        if(subject == "accessToken"){
            cal.add(Calendar.HOUR, 1);
        }
        else{
            cal.add(Calendar.DATE, 3);
        }

        Map<String, Object> payload = new HashMap<>();
        payload.put("id",id);
        payload.put("name",name);

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
    public Boolean validateToken(String token){

        return true;
    }
}
