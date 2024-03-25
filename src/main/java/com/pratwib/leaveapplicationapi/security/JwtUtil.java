package com.pratwib.leaveapplicationapi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.pratwib.leaveapplicationapi.model.entity.AppUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    @Value("${app.leaveapplication.jwt.jwt-secret}")
    private String jwtSecret;
    @Value("${app.leaveapplication.jwt.app-name}")
    private String appName;
    @Value("${app.leaveapplication.jwt.jwt-expiration}")
    private Long jwtExpiration;

    public String generateToken(AppUser appUser) {
        try {
            return JWT.create()
                    .withIssuer(appName)
                    .withSubject(appUser.getId())
                    .withExpiresAt(Instant.now().plusSeconds(jwtExpiration))
                    .withIssuedAt(Instant.now())
                    .withClaim("role", appUser.getRole().name())
                    .sign(getAlgorithm());
        } catch (JWTCreationException e) {
            throw new JWTCreationException("Failed to create JWT", e);
        }
    }

    public DecodedJWT verifyJwtToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(getAlgorithm()).build();

            return verifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Invalid JWT", e);
        }
    }

    public Map<String, String> getUserInfo(DecodedJWT decodedJWT) {
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("userId", decodedJWT.getSubject());
        userInfo.put("role", decodedJWT.getClaim("role").asString());

        return userInfo;
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }
}
