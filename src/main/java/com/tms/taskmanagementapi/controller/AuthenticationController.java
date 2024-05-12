package com.tms.taskmanagementapi.controller;

import com.tms.taskmanagementapi.dto.AuthRequest;
import com.tms.taskmanagementapi.dto.AuthResponse;
import com.tms.taskmanagementapi.dto.AuthToken;
import com.tms.taskmanagementapi.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Login APIs",
        description = "This helps to make CRUD Operations on Login"
)
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/authentication")
@Slf4j
@Validated
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
    public ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody AuthRequest authRequest){
        log.info("Entered login api");
        AuthResponse authResponse = authenticationService.authenticateUser(authRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }


    @Operation(
            summary = "Check token validity",
            description = "This API will helps check the validity of the token"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HTTP STATUS : OK"),
            @ApiResponse(responseCode = "401", description = "HTTP STATUS : UNAUTHORIZED",content = @Content),
            @ApiResponse(responseCode = "500", description = "HTTP STATUS : INTERNAL_SERVER_ERROR",content = @Content)
    })
    @PostMapping("/check-token")
    public ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody AuthToken authToken){
        log.info("Entered check-token api");
        AuthResponse authResponse = authenticationService.checkToken(authToken.getToken());
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }


}
