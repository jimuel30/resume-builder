package com.example.resumemicroservice.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private long userId;
    private String firstName;
    private String middleName;
    private String lastName;
}
