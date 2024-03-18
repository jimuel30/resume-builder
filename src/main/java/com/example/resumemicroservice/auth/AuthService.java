package com.example.resumemicroservice.auth;

import com.example.resumemicroservice.domain.LoginRequest;
import com.example.resumemicroservice.domain.RegisterRequest;
import com.example.resumemicroservice.domain.Response;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<Response> registerUser(RegisterRequest request);

    ResponseEntity<Response> login(LoginRequest loginRequest);
//
//    ResponseEntity<Response> generateFromRefreshToken(String token);



}