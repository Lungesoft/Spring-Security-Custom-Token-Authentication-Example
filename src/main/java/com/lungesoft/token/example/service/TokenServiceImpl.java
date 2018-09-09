package com.lungesoft.token.example.service;

import com.lungesoft.token.example.exception.InvalidTokenException;
import com.lungesoft.token.example.model.BaseUser;
import com.lungesoft.token.example.util.TokenConstant;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${token.secret.key}")
    private String secretKey;

    @Override
    public BaseUser retrieveBaseUser(String jwtToken) throws InvalidTokenException {
        DefaultClaims claims = retrieveDefaultClaims(jwtToken);

        Object tokenExpirationDateLong = claims.get(TokenConstant.TOKEN_EXPIRATION_DATE);
        Object userIdNumber = claims.get(TokenConstant.USER_ID);
        if (tokenExpirationDateLong == null || userIdNumber == null) {
            throw new InvalidTokenException("Invalid token");
        }
        if (new Date((Long) tokenExpirationDateLong).before(new Date())) {
            throw new InvalidTokenException("Token expired date error");
        }
        return new BaseUser(((Number) userIdNumber).longValue());
    }

    private DefaultClaims retrieveDefaultClaims(String token) throws InvalidTokenException {
        try {
            return (DefaultClaims) Jwts.parser().setSigningKey(secretKey).parse(token).getBody();
        } catch (JwtException ex) {
            throw new InvalidTokenException("Invalid token", ex);
        }
    }

    @Override
    public String generateToken(BaseUser baseUser) {
        Calendar calendar = Calendar.getInstance();

        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put(TokenConstant.USER_ID, baseUser.getId());
        tokenData.put(TokenConstant.TOKEN_CREATE_DATE, calendar.getTime());
        calendar.add(Calendar.YEAR, 100);
        tokenData.put(TokenConstant.TOKEN_EXPIRATION_DATE, calendar.getTime());

        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setExpiration(calendar.getTime());
        jwtBuilder.setClaims(tokenData);
        jwtBuilder.signWith(SignatureAlgorithm.HS512, secretKey);
        return jwtBuilder.compact();
    }
}
