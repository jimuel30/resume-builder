package com.example.resumemicroservice.service.impl;

import com.example.resumemicroservice.domain.RegisterRequest;
import com.example.resumemicroservice.enums.RoleType;
import com.example.resumemicroservice.model.Role;
import com.example.resumemicroservice.model.User;
import com.example.resumemicroservice.repo.RoleRepo;
import com.example.resumemicroservice.repo.UserRepo;
import com.example.resumemicroservice.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    private final RoleRepo roleRepo;

    public UserServiceImpl(final UserRepo userRepo,
                           final PasswordEncoder passwordEncoder,
                           final RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
    }


    @Override
    public User saveUser(RegisterRequest registerRequest){
        final Optional<Role> optionalRole = roleRepo.findById(1l);
        Role role = new Role();
        if(optionalRole.isEmpty()){
            role.setRoleType(RoleType.USER);
            role = roleRepo.save(role);
        }
        else {
            role = optionalRole.get();
        }
        final User user = new User();
        user.setRoleList(List.of(role));
        user.setEmail(registerRequest.getEmail());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCity(registerRequest.getCity());
        return userRepo.save(user);
    }



    @Override
    public boolean isExistingByEmail(final String email) {
        return  userRepo.existsByEmail(email);
    }



    @Override
    public User getUserByEmail(final String email) {
        final Optional<User> user = userRepo.findByEmail(email);
        return user.orElse(null);
    }



    @Override
    public User changePassword(final User user,
                               final String password) {
        user.setPassword(passwordEncoder.encode(password));
        final User savedUser = userRepo.save(user);

        return savedUser;
    }
}
