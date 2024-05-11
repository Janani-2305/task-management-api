package com.tms.taskmanagementapi.controller;

import com.tms.taskmanagementapi.dto.AuthRequest;
import com.tms.taskmanagementapi.dto.AuthResponse;
import com.tms.taskmanagementapi.service.AuthenticationService;
import com.tms.taskmanagementapi.service.JwtTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(
        name = "Login APIs",
        description = "This helps to make CRUD Operations on Login"
)
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/authentication")
@Slf4j
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Operation(
            summary = "Login using Username and Password",
            description = "This API will helps to Login using Username and Password"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HTTP STATUS : OK"),
            @ApiResponse(responseCode = "401", description = "HTTP STATUS : UNAUTHORIZED",content = @Content),
            @ApiResponse(responseCode = "500", description = "HTTP STATUS : INTERNAL_SERVER_ERROR",content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody AuthRequest authRequest){
        log.info("Entered login api");
        AuthResponse authResponse = authenticationService.authenticateUser(authRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }


}
