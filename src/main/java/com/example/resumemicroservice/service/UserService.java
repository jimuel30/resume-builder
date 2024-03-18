package com.example.resumemicroservice.service;

import com.example.resumemicroservice.domain.RegisterRequest;
import com.example.resumemicroservice.model.User;

public interface UserService {


    User  saveUser(RegisterRequest registerRequest);

    boolean isExistingByEmail( String email);



    User getUserByEmail(String email);



    User changePassword(User user, String password);



}