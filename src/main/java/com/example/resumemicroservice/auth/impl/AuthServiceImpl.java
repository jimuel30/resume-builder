package com.example.resumemicroservice.auth.impl;

import com.example.resumemicroservice.auth.AuthService;
import com.example.resumemicroservice.auth.JwtService;
import com.example.resumemicroservice.domain.*;
import com.example.resumemicroservice.model.User;
import com.example.resumemicroservice.service.UserService;
import org.springframework.security.core.AuthenticationException;
import com.example.resumemicroservice.util.ResEntityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;



@Service
public class AuthServiceImpl implements AuthService {


    private final UserService userService;

    private final JwtService jwtService;



    private final AuthenticationManager authenticationManager;


    public AuthServiceImpl(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public ResponseEntity<Response> registerUser(RegisterRequest request) {
        ResponseEntity<Response> responseEntity;
        if(userService.isExistingByEmail(request.getEmail())){
            responseEntity = ResEntityUtil.badRequest("Email already used");
        }
        else{
            final User user = userService.saveUser(request);
            responseEntity = ResEntityUtil.success(generateSuccessAuthToken(user));
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<Response> login(LoginRequest request) {
        ResponseEntity<Response> responseEntity;
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            ));

            final User user = userService.getUserByEmail(request.getEmail());

            responseEntity = ResEntityUtil.success(generateSuccessAuthToken(user));

        } catch (AuthenticationException e) {
            responseEntity = ResEntityUtil.badRequest(e.getMessage());
        }
        return responseEntity;
    }

    private ResponseEntity<Response> generateSuccessAuthToken(final User user){
        final Token token = jwtService.generateToken(user, 6_000_000);
        final Token refreshToken = jwtService.generateToken(user,20_000_000);
        final AuthToken authToken = AuthToken.builder()
                .token(token)
                .refreshToken(refreshToken)
                .build();
        return ResEntityUtil.success(authToken);
    }
}