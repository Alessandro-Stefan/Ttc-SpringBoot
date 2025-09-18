package com.ttc.app.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface AuthenticationService {
    String generateToken(String email);
    String createToken(Map<String,Object> claims, String email);
    Key getSignKey();
    String extractUsername(String token);
    Date extractExpiration(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    Claims extractAllClaims(String token);
    Boolean isTokenExpired(String token);
    Boolean validateToken(String token, UserDetails userDetails);
}
