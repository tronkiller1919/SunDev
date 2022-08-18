package com.returnorder.managment.authorizationservice.utility;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtility {
    @Value("{$jwt.secret}")
    private String JWT_SECRET;

    private final static long JWT_VALIDITY = 10 * 60 * 1000;


    public String generateToken(String username) {
        long nowMillis = System.currentTimeMillis();
        Date exp = new Date(nowMillis + JWT_VALIDITY);
        return Jwts.builder()
                .setSubject(username)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
    }
}
