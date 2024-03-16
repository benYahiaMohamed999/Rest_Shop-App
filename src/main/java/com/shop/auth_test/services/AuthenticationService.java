package com.shop.auth_test.services;

import com.shop.auth_test.entity.AuthenticationResponse;
import com.shop.auth_test.entity.Role;
import com.shop.auth_test.entity.UserEntity;
import com.shop.auth_test.repository.UserRepository;
import jdk.jshell.Snippet;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
     private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;



    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,

                                 AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;

        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(UserEntity request){
        UserEntity user=new UserEntity();
        user.setFirs_name(request.getFirs_name());
        user.setLast_name(request.getLast_name());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(request.getRole());

        user=userRepository.save(user);


        String token=jwtService.generateToken(user);
        return  new AuthenticationResponse(token);



    }
    public boolean checkUserExists(UserEntity user) {
        // Example: Check if a user with the same username already exists
        Optional<UserEntity> existingUser = userRepository.findByusername(user.getUsername());

        // If existingUser is not null, it means a user with the same username exists
        return existingUser.isPresent();
    }
    public AuthenticationResponse authentication(UserEntity request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()
                ));

                 UserEntity user=userRepository.findByusername(request.getUsername()).orElseThrow();

                  String token=jwtService.generateToken(user);

                  return new AuthenticationResponse(token);


    }

}
