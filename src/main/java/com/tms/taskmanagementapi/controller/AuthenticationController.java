package com.tms.taskmanagementapi.controller;

import com.tms.taskmanagementapi.dto.AuthRequest;
import com.tms.taskmanagementapi.dto.AuthResponse;
import com.tms.taskmanagementapi.service.AuthenticationService;
import com.tms.taskmanagementapi.service.JwtTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/authentication")
@Slf4j
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody AuthRequest authRequest){
        log.info("Entered login api");
        AuthResponse authResponse = authenticationService.authenticateUser(authRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }


}
