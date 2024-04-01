package com.example.resumemicroservice.auth.impl;

import com.example.resumemicroservice.auth.JwtService;
import com.example.resumemicroservice.domain.Token;
import com.example.resumemicroservice.model.User;
import com.example.resumemicroservice.modelservice.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtServiceImpl implements JwtService {

    private final UserService userService;

    private final  String SECRET_KEY;

    private static final Logger LOG = LoggerFactory.getLogger(JwtServiceImpl.class);


    public JwtServiceImpl(final UserService userService, @Value("${secrets.key}")final  String secretKey) {
        this.userService = userService;
        SECRET_KEY = secretKey;
    }


    @Override
    public Token generateToken(final UserDetails userDetails, final int timeStamp) {
        final Map<String, Object> extraClaims = new HashMap<>();
        LOG.info("Generating token");

        final Date issuedAt =new Date(System.currentTimeMillis());
        final Date expiresAt = new Date(System.currentTimeMillis()+timeStamp);

        final String tokenString =  Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedAt)
                .setExpiration(expiresAt)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

        LOG.info("Generated token validity life in Millis: {}",timeStamp);

        return Token.builder()
                .expiresAt(expiresAt)
                .token(tokenString)
                .issuedAt(issuedAt)
                .build();

    }

    @Override
    public Token generateTokenFromRefreshToken(final String token) {
        final String email = extractUserName(token);
        final User user = userService.getByEmail(email);
        Token jwtToken = null;

        if(user!=null){
            jwtToken = generateToken(user,3000);
        }
        return jwtToken;
    }

    @Override
    public String extractUserName(final String token) {
        return  extractClaim(token, Claims::getSubject);
    }

    @Override
    public User extractUser(final String token) {
        final String jwt = token.substring(7);
        final String email = extractUserName(jwt);
        return userService.getByEmail(email);
    }

    @Override
    public boolean isTokenValid(final String token, final UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private Boolean isTokenExpired(final String token){
        return  extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(final String token){
        return  extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(final String token, final Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return  claimsResolver.apply(claims);

    }

    private Claims extractAllClaims(String token){


        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey(){
        final byte[] keyBytes  = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}