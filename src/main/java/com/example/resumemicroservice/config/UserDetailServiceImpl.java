package com.example.resumemicroservice.config;

import com.example.resumemicroservice.model.User;
import com.example.resumemicroservice.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;

    public UserDetailServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepo.findByEmail(email);

        if(optionalUser.isEmpty()){
                throw  new UsernameNotFoundException("user not found");
        }

        return optionalUser.get();
    }
}