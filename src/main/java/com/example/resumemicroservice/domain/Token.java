package com.example.resumemicroservice.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Token {
    private String token;
    private Date issuedAt;
    private Date expiresAt;
}