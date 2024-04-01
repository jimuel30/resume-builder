package com.example.resumemicroservice.modelservice;

import com.example.resumemicroservice.domain.RegisterRequest;
import com.example.resumemicroservice.model.User;

public interface UserService {


    User save(RegisterRequest registerRequest);

    boolean isExistingByEmail( String email);



    User getByEmail(String email);



    User changePassword(User user, String password);



}