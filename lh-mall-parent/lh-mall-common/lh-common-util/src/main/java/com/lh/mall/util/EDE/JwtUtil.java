package com.lh.mall.util.EDE;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JwtUtil {

    private static final String SECRET = "jfkajkf";
    // subject 用户信息
    public static String createToken(String subject){
        String token = Jwts.builder().setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 20))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();

        return token;
    }

    public static String parseToken(String token){
        Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String subject = body.getSubject();
        return subject;
    }

    public static void main(String[] args) throws InterruptedException {
        String tesStr = "课堂";
        String token = createToken(tesStr);
        System.out.println(token);

        TimeUnit.SECONDS.sleep(5);

        String s = parseToken(token);
        System.out.println(s);
    }
}
