package com.example.resumemicroservice.domain;


import lombok.Data;


@Data
public class RegisterRequest {



    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String middleName;

    private String city;


}