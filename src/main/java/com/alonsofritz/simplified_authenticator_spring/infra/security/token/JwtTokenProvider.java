package com.alonsofritz.simplified_authenticator_spring.infra.security.token;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alonsofritz.simplified_authenticator_spring.domain.entities.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

@Component("jwtTokenProvider")
public class JwtTokenProvider {

    @Value("${private.rsa.key}")
    private RSAPrivateKey rsaPrivateKey;

    @Value("${public.rsa.key}")
    private RSAPublicKey rsaPublicKey;

    @Value("${jwt.expiration.time}")
    private long expiration;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
            return JWT.create()
                .withIssuer("auth0")
                .withSubject(user.getGlobalId().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .sign(algorithm);
            
        } catch (JWTCreationException exception){
            // Invalid Signing configuration / Couldn't convert Claims.
        }

        return null;
    }

    public DecodedJWT verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
            return JWT.require(algorithm)
                .withIssuer("auth0")
                .build()
                .verify(token);
        } catch (JWTVerificationException exception){
            // Invalid signature/claims
        }
    
        return null;
    }

    public String getClaimsFromToken(String token) {
        DecodedJWT jwt = verifyToken(token);
        return jwt.getClaims().toString();
    }

    public boolean isTokenExpired(String token) {
        DecodedJWT jwt = verifyToken(token);
        return jwt.getExpiresAt().before(new Date());
    }
}
