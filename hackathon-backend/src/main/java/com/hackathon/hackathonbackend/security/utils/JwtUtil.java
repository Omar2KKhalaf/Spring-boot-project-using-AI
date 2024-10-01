package com.hackathon.hackathonbackend.security.utils;


import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.hackathon.hackathonbackend.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {
    private final String secretKey;

    public JwtUtil(){
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGenerator.generateKey();
            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Long extractUserId(String token) {
        return (Long) extractClaim(token, claims -> claims.get("userId"));
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(User userModel) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userModel.getId());
        return createToken(claims, userModel.getEmail());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        SecretKey secretKey = generalKey();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 60 * 60 * 30);
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey).compact();
    }

    public SecretKey generalKey(){
        byte[] encodeKey = Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(encodeKey);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername())  && !isTokenExpired(token));
    }
}
