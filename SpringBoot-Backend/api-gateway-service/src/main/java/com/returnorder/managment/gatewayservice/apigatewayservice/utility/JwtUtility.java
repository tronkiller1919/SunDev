package com.returnorder.managment.gatewayservice.apigatewayservice.utility;

import com.returnorder.managment.gatewayservice.apigatewayservice.exception.CustomExceptionResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtility {

    @Value("{$jwt.secret}")
    private String JWT_SECRET;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public Date getExpirationDate(String jwtToken){
        return (Date) getClaimsFromJwt(jwtToken,Claims::getExpiration);
    }

    public String getUsername(String jwtToken){
        return (String) getClaimsFromJwt(jwtToken,Claims::getSubject);
    }

    public Object getClaimsFromJwt(String jwtToken, Function<Claims,Object> claimsResolver){
        Claims claims = getAllClaimsFromJwt(jwtToken);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromJwt(String jwtToken){
            return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(jwtToken).getBody();
    }

    private Boolean tokenExpired(String jwtToken){
        return getExpirationDate(jwtToken).before(new Date());
    }

    public Boolean validateToken(String jwtToken) {
        try {
            return !tokenExpired(jwtToken) && null != getUsername(jwtToken);
        }catch(SignatureException e){
            throw new SignatureException("Signature Invalid");
        }catch(ExpiredJwtException e){
            throw new CustomExceptionResponse("JWT has expired");
        }

    }

}
