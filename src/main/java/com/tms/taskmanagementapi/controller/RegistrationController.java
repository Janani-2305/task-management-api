package com.tms.taskmanagementapi.controller;

import com.tms.taskmanagementapi.dto.ErrorResponseDto;
import com.tms.taskmanagementapi.dto.RegistrationDto;
import com.tms.taskmanagementapi.dto.ResponseDto;
import com.tms.taskmanagementapi.service.RegistrationService;
import com.tms.taskmanagementapi.util.TmsUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Registration APIs",
        description = "This helps to make CRUD Operations on Registration"
)
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/user")
@Validated
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private TmsUtil tmsUtil;


    @Operation(
            summary = "Register the user",
            description = "This API will helps to Register the user"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "HTTP STATUS : CREATED"),
            @ApiResponse(responseCode = "401", description = "HTTP STATUS : UNAUTHORIZED",content = @Content),
            @ApiResponse(responseCode = "500", description = "HTTP STATUS : INTERNAL_SERVER_ERROR",content = @Content)
    })
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerUser(@Valid @RequestBody RegistrationDto registrationDto){
        tmsUtil.passwordMatcher(registrationDto);
        ResponseDto responseDto = registrationService.saveUser(registrationDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get User details by Email",
            description = "This API will helps to Get the user by passing email id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HTTP STATUS : OK"),
            @ApiResponse(responseCode = "401", description = "HTTP STATUS : UNAUTHORIZED",content = @Content),
            @ApiResponse(responseCode = "500", description = "HTTP STATUS : INTERNAL_SERVER_ERROR",content = @Content)
    })
    @GetMapping("/{email}")
    public ResponseEntity<RegistrationDto> getUser(@PathVariable
                                                       @Email(message = "Email address should be a valid value")
                                                       @NotEmpty(message = "Email cannot be empty")
                                                       String email){
        RegistrationDto registrationDto = registrationService.getUserByEmail(email);
        return new ResponseEntity<>(registrationDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Update the User",
            description = "This API will helps to Update the user"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HTTP STATUS : OK"),
            @ApiResponse(responseCode = "401", description = "HTTP STATUS : UNAUTHORIZED",content = @Content),
            @ApiResponse(responseCode = "404",description = "HTTP STATUS : NOT FOUND",content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "HTTP STATUS : INTERNAL_SERVER_ERROR",content = @Content)
    })
    @PutMapping("/{email}")
    public ResponseEntity<ResponseDto> updaterUser(@Valid @RequestBody RegistrationDto registrationDto,
                                                   @PathVariable
                                                   @Email(message = "Email address should be a valid value")
                                                   @NotEmpty(message = "Email cannot be empty") String email){
        tmsUtil.passwordMatcher(registrationDto);
        ResponseDto responseDto = registrationService.updateUser(registrationDto, email);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Validate the username",
            description = "Validate the user before Update the password for forgot password functionality"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HTTP STATUS : OK"),
            @ApiResponse(responseCode = "401", description = "HTTP STATUS : UNAUTHORIZED",content = @Content),
            @ApiResponse(responseCode = "404",description = "HTTP STATUS : NOT FOUND",content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "HTTP STATUS : INTERNAL_SERVER_ERROR",content = @Content)
    })
    @PostMapping("/validate-username")
    public ResponseEntity<ResponseDto> validateUserName(@RequestBody
                                                            @Email(message = "Email address should be a valid value")
                                                            @NotEmpty(message = "Email cannot be empty")
                                                            String email){
        ResponseDto responseDto = registrationService.validateUserName(email);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Update Password",
            description = "Update the password for forgot password functionality"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HTTP STATUS : OK"),
            @ApiResponse(responseCode = "401", description = "HTTP STATUS : UNAUTHORIZED",content = @Content),
            @ApiResponse(responseCode = "404",description = "HTTP STATUS : NOT FOUND",content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "HTTP STATUS : INTERNAL_SERVER_ERROR",content = @Content)
    })
    @PutMapping("/update-password")
    public ResponseEntity<ResponseDto> updatePassword(@Valid @RequestBody RegistrationDto registrationDto){
        tmsUtil.passwordMatcher(registrationDto);
        ResponseDto responseDto = registrationService.updatePassword(registrationDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
