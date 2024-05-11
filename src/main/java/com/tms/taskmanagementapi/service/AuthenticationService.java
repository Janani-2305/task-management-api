package com.tms.taskmanagementapi.service;

import com.tms.taskmanagementapi.dto.AuthRequest;
import com.tms.taskmanagementapi.dto.AuthResponse;
import com.tms.taskmanagementapi.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RegistrationRepository registrationRepository;

    public AuthResponse authenticateUser(AuthRequest authRequest) {

        AuthResponse authResponse = new AuthResponse();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            authResponse.setToken(jwtTokenService.generateToken(authRequest.getUserName()));
            authResponse.setName(registrationRepository.findByEmail(authRequest.getUserName()).get().getName());
            authResponse.setUserName(authRequest.getUserName());
        } else {
            throw new AuthenticationCredentialsNotFoundException("Username or Password invalid");
        }

        return authResponse;
    }
}
