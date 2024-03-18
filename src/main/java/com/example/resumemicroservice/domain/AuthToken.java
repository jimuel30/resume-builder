package com.example.resumemicroservice.domain;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class AuthToken {
    private Token token;
    private Token refreshToken;
}