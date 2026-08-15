package com.tertal.konpeito.security;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtils {

    private SecretKey secretKey;

    @PostConstruct
    public void init() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        this.secretKey = keyGenerator.generateKey();
    }

    public String generateToken(String username) {
        // 1 hour in milliseconds
        int EXPIRATION_DURATION = 1000 * 60 * 60;

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_DURATION))
                .signWith(secretKey)
                .compact();
    }


    public String extractUsername(String token) {
        return Jwts.parser().verifyWith(this.secretKey).build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        boolean isUsernameCorrect = extractUsername(token).equals(userDetails.getUsername());
        return isUsernameCorrect && isNotExpired(token);
    }

    private boolean isNotExpired(String token) {
        Date expiration = Jwts.parser().verifyWith(this.secretKey).build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();

        return expiration.after(new Date(System.currentTimeMillis()));
    }
}
