package com.example.resumemicroservice.domain;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String email;
    private String password;
}