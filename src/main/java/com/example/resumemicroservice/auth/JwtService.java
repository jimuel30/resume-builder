package com.example.resumemicroservice.auth;

import com.example.resumemicroservice.domain.Token;
import com.example.resumemicroservice.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    Token generateToken(UserDetails userDetails, int duration);

    Token generateTokenFromRefreshToken(String token);

    String extractUserName( String token);

    User extractUser(String token);

    boolean isTokenValid( String token, UserDetails userDetails);
}
