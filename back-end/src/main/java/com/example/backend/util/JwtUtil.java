package com.example.backend.util;

import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

public class JwtUtil {
    private static final String JWT_SECRET="8677df7fc3a34e26a61c034d5ec8245d";
    public static SecretKey generalKey(){
        byte[] encodedKey= Base64.getDecoder().decode(JWT_SECRET);
        return new SecretKeySpec(encodedKey,0,encodedKey.length,"AES");
    }

    public static String createJWT(final String id,final String subject){
        Date now=new Date();
        JwtBuilder jwtBuilder= Jwts.builder()
                .setId(id)
                .setSubject(subject)
                .setIssuer("admin")
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256,generalKey());
        return jwtBuilder.compact();
    }

    public static Claims parse(String token){
        if (token==null || "".equals(token)){
            return null;
        }
        Claims claims=null;
        try {
            claims=Jwts.parser()
                    .setSigningKey(generalKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e){
            System.out.println("解析失败");
        }
        return claims;
    }
}
