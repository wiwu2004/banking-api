package com.wiwu.bankaapi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtUtil {
    private final String SECRET = "S3cr3T";

    public String generateToken(String username){
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 ))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public  String extractUsername(String token){
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token);

        return  decodedJWT.getSubject();
    }

    public boolean validateToken(String token, String username){
        String extractedUsername = extractUsername(token);
        return extractedUsername.equals(username);
    }
}
